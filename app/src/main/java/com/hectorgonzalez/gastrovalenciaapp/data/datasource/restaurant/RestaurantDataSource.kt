package com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.api.RestaurantApi
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.dto.RestaurantDto
import com.hectorgonzalez.gastrovalenciaapp.data.networkClient.NetworkClient

class RestaurantDataSource {
    private val restaurantApi = NetworkClient.instance.create(RestaurantApi::class.java)

    suspend fun getRestaurants(userId: String): List<RestaurantDto> {
        return restaurantApi.getRestaurants(userId)
    }
    suspend fun getRestaurantById(restaurantId : String, userId : String): RestaurantDto{
        return restaurantApi.getRestaurantById(restaurantId, userId)
    }
    suspend fun getRestaurantsByName(name : String): List<RestaurantDto>{
        return restaurantApi.getRestaurantsByName(name)
    }
    suspend fun listRestaurantLikes(userId:String) : List<RestaurantDto> {
        return restaurantApi.listRestaurantLikes(userId)
    }
    suspend fun toggleRestaurantLike(restaurantId : String, userId : String) {
        return restaurantApi.likeRestaurants(restaurantId, userId)
    }

}