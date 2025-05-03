package com.hectorgonzalez.gastrovalenciaapp.domain.entity

data class Restaurant(
    val id: Long,
    val name: String,
    val foodType: String,
    val address: String,
    val rating: Double,
    val averagePrice: Double,
    val restaurantImage: String,
    val menuImage: String,
    val description: String
)