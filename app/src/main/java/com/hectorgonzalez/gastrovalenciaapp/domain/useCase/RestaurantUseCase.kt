package com.hectorgonzalez.gastrovalenciaapp.domain.useCase

import com.hectorgonzalez.gastrovalenciaapp.data.repository.RestaurantRepository
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Restaurant

class RestaurantUseCase(private val repository: RestaurantRepository = RestaurantRepository()) {
    suspend fun getRestaurants(): List<Restaurant> = repository.getRestaurants()
    suspend fun getRestaurantById(id: String): Restaurant = repository.getRestaurantById(id)
    suspend fun getRestaurantsByName(name: String): List<Restaurant> =
        repository.getRestaurantsByName(name)
}