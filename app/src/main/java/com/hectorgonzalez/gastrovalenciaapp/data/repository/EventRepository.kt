package com.hectorgonzalez.gastrovalenciaapp.data.repository

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.EventDataSource
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto.UserDto
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.User
import java.time.LocalDateTime


class EventRepository(
    private val remoteDataSource: EventDataSource = EventDataSource()
) {
    suspend fun getEvents(): List<Event> {
        return remoteDataSource.getAllEvents().map { it.toDomain() }
    }
}

private fun EventDto.toDomain(): Event {
    return Event(
        id = this.id.toInt(),
        name = this.name,
        category = this.category,
        location = this.location,
        date = this.date,
        price = this.price,
        description = this.description,
        duration = this.duration
    )
}
