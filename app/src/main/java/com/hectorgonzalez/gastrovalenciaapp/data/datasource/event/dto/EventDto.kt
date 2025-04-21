package com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto

import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event

data class EventDto(
    val id: Int,
    val name: String,
    val category: String,
    val location: String,
    val date: String,
    val price: Double,
    val description: String,
    val duration: String
) {
    fun toDomain(): Event {
        return Event(id, name, category, location, date, price, description, duration)
    }
}