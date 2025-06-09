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

    // Lista de restaurantes mostrados en la pantalla
    var restaurants by mutableStateOf<List<Restaurant>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    // Si ocurre algún error, lo guardamos aquí para mostrarlo en la UI
    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isLikingInProgress by mutableStateOf(false)
        private set

    // Llama al UseCase para obtener la lista de restaurantes
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

    // Busca restaurantes por nombre
    fun searchRestaurantsByName(name: String, context: Context? = null) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            isLoading = true
            try {
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

    // Alterna el "like" de un restaurante
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
                restaurants = restaurants.map { restaurant ->
                    if (restaurant.id == restaurantId) {
                        restaurant.copy(liked = newLikedState)
                    } else {
                        restaurant
                    }
                }

                restaurantsUseCase.toggleRestaurantLike(restaurantId.toString(), userId.toString())


            } catch (e: Exception) {
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

    // Limpia cualquier error que se esté mostrando en la pantalla
    fun clearError() {
        errorMessage = null
    }
}