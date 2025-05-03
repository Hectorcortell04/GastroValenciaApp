package com.hectorgonzalez.gastrovalenciaapp.domain.useCase

import com.hectorgonzalez.gastrovalenciaapp.data.repository.EventRepository
import com.hectorgonzalez.gastrovalenciaapp.data.repository.RestaurantRepository
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Restaurant

class RestaurantUseCase(private val repository: RestaurantRepository = RestaurantRepository()) {
    suspend fun getRestaurants(): List<Restaurant> = repository.getRestaurants()
}