package com.hectorgonzalez.gastrovalenciaapp.domain.entity

data class Event(
    val id: Int,
    val name: String,
    val category: String,
    val location: String,
    val date: String,
    val price: Double,
    val description: String,
    val duration: String
)
