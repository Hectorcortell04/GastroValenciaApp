import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Restaurant
import com.hectorgonzalez.gastrovalenciaapp.domain.useCase.RestaurantUseCase
import com.hectorgonzalez.gastrovalenciaapp.utils.UserManager
import kotlinx.coroutines.launch

class RestaurantDetailViewModel(
    private val restaurantUseCase: RestaurantUseCase = RestaurantUseCase()
) : ViewModel() {

    // Estado del restaurante actual
    var restaurant by mutableStateOf<Restaurant?>(null)
        private set

    // Estado de carga
    var isLoading by mutableStateOf(false)
        private set

    // Estado de error
    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Estado de carga para el like (para mostrar loading en el botón)
    var isLikingInProgress by mutableStateOf(false)
        private set

    /**
     * Carga un restaurante específico por su ID
     */
    fun loadRestaurant(restaurantId: Int, context: Context) {
        val userId = UserManager.getUserId(context)

        if (userId == null) {
            errorMessage = "Usuario no encontrado. Por favor, inicia sesión nuevamente."
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val loadedRestaurant = restaurantUseCase.getRestaurantById(
                    restaurantId.toString(),
                    userId = userId.toString()
                )
                restaurant = loadedRestaurant
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error desconocido al cargar el restaurante"
                restaurant = null
            } finally {
                isLoading = false
            }
        }
    }

    /**
     * Alterna el estado de like del restaurante actual
     * El método toggleLike maneja automáticamente like/unlike
     */
    fun toggleLike(context: Context) {
        val currentRestaurant = restaurant ?: return
        val userId = UserManager.getUserId(context)

        if (userId == null) {
            errorMessage = "Error: Usuario no encontrado. Por favor, inicia sesión nuevamente."
            return
        }

        viewModelScope.launch {
            isLikingInProgress = true

            // Guardamos el estado actual por si hay error
            val previousLikedState = currentRestaurant.liked
            val newLikedState = !previousLikedState // 🔥 CALCULAR EL NUEVO ESTADO

            try {
                // ✅ CORRECCIÓN: Actualizamos optimísticamente la UI con el estado OPUESTO
                restaurant = currentRestaurant.copy(liked = newLikedState)

                // Llamamos al servidor
                restaurantUseCase.toggleRestaurantLike(currentRestaurant.id.toString(), userId.toString())

                // Si llegamos aquí, la operación fue exitosa
                // El estado ya está actualizado optimísticamente con el valor correcto

            } catch (e: Exception) {
                // En caso de error, revertimos al estado anterior
                restaurant = currentRestaurant.copy(liked = previousLikedState)
                errorMessage = "Error al actualizar favorito: ${e.localizedMessage}"
            } finally {
                isLikingInProgress = false
            }
        }
    }

    /**
     * Versión alternativa más segura (sin actualización optimista)
     * Úsala si sigues teniendo problemas con la versión optimista
     */
    fun toggleLikeSafe(context: Context) {
        val currentRestaurant = restaurant ?: return
        val userId = UserManager.getUserId(context)

        if (userId == null) {
            errorMessage = "Error: Usuario no encontrado. Por favor, inicia sesión nuevamente."
            return
        }

        viewModelScope.launch {
            isLikingInProgress = true

            try {
                // Llamar al servidor SIN actualización optimista
                restaurantUseCase.toggleRestaurantLike(currentRestaurant.id.toString(), userId.toString())

                // Obtener el estado real desde el servidor
                val updatedRestaurant = restaurantUseCase.getRestaurantById(
                    currentRestaurant.id.toString(),
                    userId.toString()
                )
                restaurant = updatedRestaurant

            } catch (e: Exception) {
                errorMessage = "Error al actualizar favorito: ${e.localizedMessage}"
            } finally {
                isLikingInProgress = false
            }
        }
    }

    /**
     * Reintenta la carga del restaurante en caso de error
     */
    fun retryLoadRestaurant(context: Context) {
        val currentRestaurant = restaurant
        if (currentRestaurant != null) {
            loadRestaurant(currentRestaurant.id, context)
        }
    }

    /**
     * Limpia el mensaje de error
     */
    fun clearError() {
        errorMessage = null
    }

    /**
     * Limpia el estado del ViewModel
     */
    override fun onCleared() {
        super.onCleared()
        restaurant = null
        errorMessage = null
        isLoading = false
        isLikingInProgress = false
    }
}