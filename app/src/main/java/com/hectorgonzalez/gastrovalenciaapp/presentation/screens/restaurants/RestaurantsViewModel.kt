package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.restaurants

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Restaurant
import com.hectorgonzalez.gastrovalenciaapp.domain.useCase.RestaurantUseCase
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

    init {
        fetchRestaurants()
    }

    private fun fetchRestaurants() {
        viewModelScope.launch {
            isLoading = true
            try {
                restaurants = restaurantsUseCase.getRestaurants()
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error desconocido"
            } finally {
                isLoading = false
            }
        }
    }

    fun searchRestaurantsByName(name: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            isLoading = true
            try {
                restaurants = restaurantsUseCase.getRestaurantsByName(name)
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error desconocido"
            } finally {
                isLoading = false
            }
        }
    }
}