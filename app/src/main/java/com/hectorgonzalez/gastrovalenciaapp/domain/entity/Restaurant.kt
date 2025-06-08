package com.hectorgonzalez.gastrovalenciaapp.domain.entity

data class Restaurant(
    val id: Int,
    val name: String,
    val foodType: String,
    val address: String,
    val rating: Double,
    val averagePrice: Double,
    val restaurantImages: List<String>,
    val menuImage: String,
    val description: String,
    val liked: Boolean,
    val restaurantWeb: String?
)