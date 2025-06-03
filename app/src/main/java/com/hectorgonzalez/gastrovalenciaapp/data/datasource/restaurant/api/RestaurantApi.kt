package com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.api

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.dto.RestaurantDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantApi {
    @GET("/restaurants")
    suspend fun getRestaurants(): List<RestaurantDto>
    @GET("/restaurants/{id}")
    suspend fun getRestaurantById(@Path("id") id: String): RestaurantDto
    @GET("/restaurants/search")
    suspend fun getRestaurantsByName(@Query("name") name: String) : List<RestaurantDto>

}