package com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto

data class EventDto(
    val id: Int,
    val name: String,
    val category: String,
    val location: String,
    val date: String,
    val time: String?,
    val price: Double,
    val description: String,
    val duration: String,
    val eventImage: String
)