package com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.api

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.dto.RestaurantDto
import retrofit2.http.GET

interface RestaurantApi {
    @GET("/restaurants")
    suspend fun getRestaurants(): List<RestaurantDto>
}