package com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.api

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.dto.RestaurantDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

// Interfaz con las llamadas a la API relacionadas con los restaurantes
interface RestaurantApi {
    @GET("/restaurants/user/{userId}")
    suspend fun getRestaurants(@Path("userId") userId: String): List<RestaurantDto>

    @GET("/restaurants/{restaurantId}/{userId}")
    suspend fun getRestaurantById(
        @Path("restaurantId") restaurantId: String,
        @Path("userId") userId: String
    ): RestaurantDto

    @GET("/restaurants/search")
    suspend fun getRestaurantsByName(@Query("name") name: String): List<RestaurantDto>

    @GET("/restaurant_likes/user/{userId}")
    suspend fun listRestaurantLikes(@Path("userId") userId: String): List<RestaurantDto>

    @POST("/restaurant_likes/{restaurantId}/like/{userId}")
    suspend fun likeRestaurants(
        @Path("restaurantId") eventId: String,
        @Path("userId") userId: String
    )
}