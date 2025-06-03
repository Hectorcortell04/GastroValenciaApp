package com.hectorgonzalez.gastrovalenciaapp.data.repository

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.RestaurantDataSource
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.dto.RestaurantDto
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Restaurant

class RestaurantRepository(
    private val remoteDataSource: RestaurantDataSource = RestaurantDataSource()
) {
    suspend fun getRestaurants(): List<Restaurant> {
        return remoteDataSource.getRestaurants().map { it.toDomain() }
    }
    suspend fun getRestaurantById(id : String): Restaurant{
        return remoteDataSource.getRestaurantById(id).toDomain()
    }
    suspend fun getRestaurantsByName(name :String): List<Restaurant> {
        return remoteDataSource.getRestaurantsByName(name).map { it.toDomain() }
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
        isLike = this.isLike,
        restaurantWeb = this.restaurantWeb
    )
}