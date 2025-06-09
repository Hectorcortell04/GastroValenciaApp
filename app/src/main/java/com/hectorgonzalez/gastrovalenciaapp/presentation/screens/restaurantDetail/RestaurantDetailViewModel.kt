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

    // Carga el restaurante con su información completa desde el backend
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

    // Cambia el estado de "favorito" del restaurante actual
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
                restaurant = currentRestaurant.copy(liked = newLikedState)

                restaurantUseCase.toggleRestaurantLike(
                    currentRestaurant.id.toString(),
                    userId.toString()
                )


            } catch (e: Exception) {
                restaurant = currentRestaurant.copy(liked = previousLikedState)
                errorMessage = "Error al actualizar favorito: ${e.localizedMessage}"
            } finally {
                isLikingInProgress = false
            }
        }
    }

    // Reintenta cargar el restaurante si ya tengo alguno cargado
    fun retryLoadRestaurant(context: Context) {
        val currentRestaurant = restaurant
        if (currentRestaurant != null) {
            loadRestaurant(currentRestaurant.id, context)
        }
    }

//Limpiar mensaje de error
    fun clearError() {
        errorMessage = null
    }

    override fun onCleared() {
        super.onCleared()
        restaurant = null
        errorMessage = null
        isLoading = false
        isLikingInProgress = false
    }
}