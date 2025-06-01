package com.hectorgonzalez.gastrovalenciaapp.data.repository

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.EventDataSource
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event

class EventRepository(
    private val remoteDataSource: EventDataSource = EventDataSource()
) {
    suspend fun getEvents(): List<Event> {
        return remoteDataSource.getAllEvents().map { it.toDomain() }
    }
}

private fun EventDto.toDomain(): Event {
    return Event(
        id = this.id,
        name = this.name,
        category = this.category,
        location = this.location,
        date = this.date,
        time = this.time ?: "19.00",
        price = this.price,
        description = this.description,
        duration = this.duration
    )
}
