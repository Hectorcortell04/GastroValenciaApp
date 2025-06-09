package com.hectorgonzalez.gastrovalenciaapp.domain.useCase

import com.hectorgonzalez.gastrovalenciaapp.data.repository.RestaurantRepository
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Restaurant

// UseCase encargado de obtener los datos de los restaurantes. Se comunica con el repositorio
class RestaurantUseCase(private val repository: RestaurantRepository = RestaurantRepository()) {
    suspend fun getRestaurants(userId: String): List<Restaurant> = repository.getRestaurants(userId)
    suspend fun getRestaurantById(restaurantId: String, userId: String): Restaurant =
        repository.getRestaurantById(restaurantId, userId)

    suspend fun getRestaurantsByName(name: String): List<Restaurant> =
        repository.getRestaurantsByName(name)

    suspend fun listRestaurantLikes(userId: String): List<Restaurant> =
        repository.listRestaurantLikes(userId)

    suspend fun toggleRestaurantLike(restaurantId: String, userId: String) =
        repository.toggleRestaurantLike(restaurantId, userId)

}