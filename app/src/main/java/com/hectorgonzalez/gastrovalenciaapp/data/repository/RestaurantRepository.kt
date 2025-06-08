package com.hectorgonzalez.gastrovalenciaapp.data.repository

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.RestaurantDataSource
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.dto.RestaurantDto
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Restaurant
import retrofit2.http.Path

class RestaurantRepository(
    private val remoteDataSource: RestaurantDataSource = RestaurantDataSource()
) {
    suspend fun getRestaurants(userId: String): List<Restaurant> {
        return remoteDataSource.getRestaurants(userId).map { it.toDomain() }
    }
    suspend fun getRestaurantById(restaurantId : String, userId: String): Restaurant{
        return remoteDataSource.getRestaurantById(restaurantId,userId).toDomain()
    }
    suspend fun getRestaurantsByName(name :String): List<Restaurant> {
        return remoteDataSource.getRestaurantsByName(name).map { it.toDomain() }
    }
    suspend fun listRestaurantLikes(userId :String): List<Restaurant> {
        return remoteDataSource.listRestaurantLikes(userId).map { it.toDomain() }
    }
    suspend fun toggleRestaurantLike(restaurantId : String, userId : String) {
        return remoteDataSource.toggleRestaurantLike(restaurantId, userId)
    }
}

fun RestaurantDto.toDomain(): Restaurant {
    return Restaurant(
        id = this.id,
        name = this.name,
        foodType = this.foodType,
        address = this.address,
        rating = this.rating,
        averagePrice = this.averagePrice,
        restaurantImages = this.restaurantImages,
        menuImage = this.menuImage ?: "",
        description = this.description,
        liked = this.liked,
        restaurantWeb = this.restaurantWeb
    )
}