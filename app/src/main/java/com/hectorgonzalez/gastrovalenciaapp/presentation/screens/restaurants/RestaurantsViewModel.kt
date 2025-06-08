package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.restaurants

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Restaurant
import com.hectorgonzalez.gastrovalenciaapp.domain.useCase.RestaurantUseCase
import com.hectorgonzalez.gastrovalenciaapp.utils.UserManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RestaurantsViewModel(
    private val restaurantsUseCase: RestaurantUseCase = RestaurantUseCase()
) : ViewModel() {
    private var searchJob: Job? = null

    var restaurants by mutableStateOf<List<Restaurant>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isLikingInProgress by mutableStateOf(false)
        private set

    fun fetchRestaurants(context: Context) {
        val userId = UserManager.getUserId(context)

        if (userId == null) {
            errorMessage = "Usuario no encontrado. Por favor, inicia sesión nuevamente."
            return
        }

        viewModelScope.launch {
            isLoading = true
            try {
                restaurants = restaurantsUseCase.getRestaurants(userId = userId.toString())
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error desconocido"
            } finally {
                isLoading = false
            }
        }
    }

    fun searchRestaurantsByName(name: String, context: Context? = null) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            isLoading = true
            try {
                // Si tienes contexto, incluir userId para obtener el estado de likes
                val userId = context?.let { UserManager.getUserId(it) }
                restaurants = if (userId != null) {
                    restaurantsUseCase.getRestaurantsByName(name)
                } else {
                    restaurantsUseCase.getRestaurantsByName(name)
                }
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error desconocido"
            } finally {
                isLoading = false
            }
        }
    }

    /**
     * Alterna el estado de like de un restaurante
     */
    fun toggleRestaurantLike(restaurantId: Int, context: Context) {
        val userId = UserManager.getUserId(context)

        if (userId == null) {
            errorMessage = "Usuario no encontrado. Por favor, inicia sesión nuevamente."
            return
        }

        viewModelScope.launch {
            isLikingInProgress = true

            // Encontrar el restaurante en la lista actual
            val currentRestaurant = restaurants.find { it.id == restaurantId }
            if (currentRestaurant == null) {
                errorMessage = "Restaurante no encontrado"
                isLikingInProgress = false
                return@launch
            }

            // Guardar el estado anterior para poder revertir en caso de error
            val previousLikedState = currentRestaurant.liked
            val newLikedState = !previousLikedState

            try {
                // ✅ ACTUALIZACIÓN OPTIMISTA: Cambiar la UI INMEDIATAMENTE
                restaurants = restaurants.map { restaurant ->
                    if (restaurant.id == restaurantId) {
                        restaurant.copy(liked = newLikedState)
                    } else {
                        restaurant
                    }
                }

                // Ahora hacer la llamada al servidor
                restaurantsUseCase.toggleRestaurantLike(restaurantId.toString(), userId.toString())

                // Si llegamos aquí, la operación fue exitosa
                // La UI ya está actualizada con el estado correcto

            } catch (e: Exception) {
                // ❌ ERROR: Revertir el cambio en la UI
                restaurants = restaurants.map { restaurant ->
                    if (restaurant.id == restaurantId) {
                        restaurant.copy(liked = previousLikedState)
                    } else {
                        restaurant
                    }
                }
                errorMessage = "Error al actualizar favorito: ${e.localizedMessage}"
            } finally {
                isLikingInProgress = false
            }
        }
    }

    /**
     * Versión alternativa más segura que actualiza desde el servidor
     * Úsala solo si la versión optimista te da problemas
     */
    fun toggleRestaurantLikeSafe(restaurantId: Int, context: Context) {
        val userId = UserManager.getUserId(context)

        if (userId == null) {
            errorMessage = "Usuario no encontrado. Por favor, inicia sesión nuevamente."
            return
        }

        viewModelScope.launch {
            isLikingInProgress = true

            try {
                // Hacer la llamada al servidor sin actualización optimista
                restaurantsUseCase.toggleRestaurantLike(restaurantId.toString(), userId.toString())

                // Obtener el restaurante actualizado desde el servidor
                val updatedRestaurant = restaurantsUseCase.getRestaurantById(
                    restaurantId.toString(),
                    userId.toString()
                )

                if (updatedRestaurant != null) {
                    // Actualizar solo este restaurante en la lista
                    restaurants = restaurants.map { restaurant ->
                        if (restaurant.id == restaurantId) {
                            updatedRestaurant
                        } else {
                            restaurant
                        }
                    }
                }

            } catch (e: Exception) {
                errorMessage = "Error al actualizar favorito: ${e.localizedMessage}"
            } finally {
                isLikingInProgress = false
            }
        }
    }

    fun clearError() {
        errorMessage = null
    }

    fun retryFetchRestaurants(context: Context) {
        fetchRestaurants(context)
    }
}