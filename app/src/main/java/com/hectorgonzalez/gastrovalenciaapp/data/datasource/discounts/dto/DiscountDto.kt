package com.hectorgonzalez.gastrovalenciaapp.data.datasource.discounts.dto

data class DiscountDto(
    val id: Int,
    val userId: Int,
    val membershipLevelId: Int,
    val discounts: List<String>
)