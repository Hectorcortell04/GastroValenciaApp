package com.hectorgonzalez.gastrovalenciaapp.domain.entity

data class Discount(
    val id: Int,
    val userId: Int,
    val membershipLevelId: Int,
    val discounts: List<String>
)
