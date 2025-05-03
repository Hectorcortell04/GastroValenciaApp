package com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.api.EventApi
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.api.RestaurantApi
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.dto.RestaurantDto
import com.hectorgonzalez.gastrovalenciaapp.data.networkClient.NetworkClient

class RestaurantDataSource {
    private val restaurantApi = NetworkClient.instance.create(RestaurantApi::class.java)

    suspend fun getRestaurants() : List<RestaurantDto>{
        return restaurantApi.getRestaurants()
    }
}