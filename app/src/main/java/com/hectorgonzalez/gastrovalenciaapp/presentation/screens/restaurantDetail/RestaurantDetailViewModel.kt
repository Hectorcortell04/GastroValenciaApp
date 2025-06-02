package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.restaurantDetail

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
    private val restaurantsUseCase: RestaurantUseCase = RestaurantUseCase()
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

    // Estado de favorito/like
    var isLiked by mutableStateOf(false)
        private set

    // Estado de carga para el like (para mostrar loading en el botón)
    var isLikingInProgress by mutableStateOf(false)
        private set

    /**
     * Carga un restaurante específico por su ID
     */
    fun loadRestaurant(restaurantId: Int, context: Context) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val loadedRestaurant = restaurantsUseCase.getRestaurantById(restaurantId.toString())
                restaurant = loadedRestaurant

                // Verificar si el restaurante está marcado como favorito/liked
                checkIfRestaurantIsLiked(restaurantId, context)

            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error desconocido al cargar el restaurante"
                restaurant = null
            } finally {
                isLoading = false
            }
        }
    }

    /**
     * Verifica si el restaurante actual está marcado como favorito
     * Nota: Aquí asumo que tienes un método para verificar si está liked.
     * Si no lo tienes, puedes omitir esta verificación o implementarla según tu lógica.
     */
    private suspend fun checkIfRestaurantIsLiked(restaurantId: Int, context: Context) {
        try {
            val userId = UserManager.getUserId(context)
            if (userId != null) {
                // Si tienes un método para verificar si está liked:
                // isLiked = restaurantsUseCase.isRestaurantLiked(restaurantId.toString(), userId.toString())

                // Si no tienes el método, por ahora lo dejamos en false
                isLiked = false
            }
        } catch (e: Exception) {
            // Si hay error al verificar likes, simplemente no marcamos como liked
            isLiked = false
        }
    }

    /**
     * Alterna el estado de like del restaurante actual
     * El método likeRestaurant maneja automáticamente like/unlike
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
            try {
               // restaurantsUseCase.likeRestaurant(currentRestaurant.id.toString(), userId.toString())
                isLiked = !isLiked

            } catch (e: Exception) {
                errorMessage = "Error al actualizar like: ${e.localizedMessage}"
                // No cambiamos isLiked para mantener el estado anterior en caso de error
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
     * Maneja la acción de reservar (placeholder para futura implementación)
     */
    fun onReserveClick() {
        // TODO: Implementar lógica de reserva
        // Ejemplo: navegar a web externa, abrir formulario de reserva, etc.
    }

    /**
     * Maneja la acción de ver el menú completo
     */
    fun onViewMenuClick() {
        // TODO: Implementar lógica para mostrar menú completo
        // Esto podría activar un estado para mostrar un diálogo o navegar a otra pantalla
    }

    /**
     * Maneja la acción de compartir restaurante
     */
    fun shareRestaurant() {
        val currentRestaurant = restaurant ?: return

        // TODO: Implementar lógica para compartir restaurante
        // Ejemplo: crear intent de compartir con información del restaurante
    }

    /**
     * Limpia el estado del ViewModel
     */
    override fun onCleared() {
        super.onCleared()
        restaurant = null
        errorMessage = null
        isLoading = false
        isLiked = false
        isLikingInProgress = false
    }
}