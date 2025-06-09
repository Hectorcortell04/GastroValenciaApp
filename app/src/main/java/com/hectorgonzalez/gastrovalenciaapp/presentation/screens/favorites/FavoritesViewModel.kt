package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.favorites

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Restaurant
import com.hectorgonzalez.gastrovalenciaapp.domain.useCase.EventsUseCase
import com.hectorgonzalez.gastrovalenciaapp.domain.useCase.RestaurantUseCase
import com.hectorgonzalez.gastrovalenciaapp.utils.UserManager
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val eventsUseCase: EventsUseCase = EventsUseCase(),
    private val restaurantsUseCase: RestaurantUseCase = RestaurantUseCase()
) : ViewModel() {
    // Lista de eventos favoritos
    var events by mutableStateOf<List<Event>>(emptyList())
        private set

    // Lista de restaurantes favoritos
    var restaurants by mutableStateOf<List<Restaurant>>(emptyList())
        private set

    // Mensaje de error si algo falla
    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Flag para mostrar carga
    var isLoading by mutableStateOf(false)
        private set

    // Carga los eventos favoritos del usuario
    fun fetchEvents(context: Context) {
        val userId = UserManager.getUserId(context)

        if (userId == null) {
            errorMessage = "Usuario no encontrado. Por favor, inicia sesión nuevamente."
            return
        }

        viewModelScope.launch {
            isLoading = true
            try {
                events = eventsUseCase.listEventsLikes(userId = userId.toString())
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error desconocido"
            } finally {
                isLoading = false
            }
        }
    }

    // Carga los restaurantes favoritos del usuario
    fun fetchRestaurants(context: Context) {
        val userId = UserManager.getUserId(context)

        if (userId == null) {
            errorMessage = "Usuario no encontrado. Por favor, inicia sesión nuevamente."
            return
        }
        viewModelScope.launch {
            isLoading = true
            try {
                restaurants = restaurantsUseCase.listRestaurantLikes(userId = userId.toString())
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error desconocido"
            } finally {
                isLoading = false
            }
        }
    }
}