package com.hectorgonzalez.gastrovalenciaapp.data.repository

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.EventDataSource
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.RestaurantDataSource
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.dto.RestaurantDto
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.UserDataSource
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto.UserDto
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Restaurant
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.User
import java.time.LocalDateTime



class RestaurantRepository(
    private val remoteDataSource: RestaurantDataSource = RestaurantDataSource()
) {
    suspend fun getRestaurants(): List<Restaurant> {
        return remoteDataSource.getRestaurants().map { it.toDomain() }
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
        restaurantImage = this.restaurantImage,
        menuImage = this.menuImage,
        description = this.description
    )
}