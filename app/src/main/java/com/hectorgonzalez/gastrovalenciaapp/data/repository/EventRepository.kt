package com.hectorgonzalez.gastrovalenciaapp.data.repository

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.EventDataSource
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event

//Aquí mapeamos de el objeto de la base de datos (dto) a nuestra entidad local.
class EventRepository(
    private val remoteDataSource: EventDataSource = EventDataSource()
) {
    suspend fun getEvents(userId: String): List<Event> {
        return remoteDataSource.getAllEvents(userId).map { it.toDomain() }
    }

    suspend fun getEventsByName(name: String): List<Event> {
        return remoteDataSource.getEventsByName(name).map { it.toDomain() }
    }

    suspend fun getEventById(eventId: String, userId: String): Event {
        return remoteDataSource.getEventById(eventId, userId).toDomain()
    }

    suspend fun likeEvent(eventId: String, userId: String) {
        return remoteDataSource.likeEvent(eventId, userId)
    }

    suspend fun listEventsLikes(userId: String): List<Event> {
        return remoteDataSource.listEventsLikes(userId).map { it.toDomain() }
    }

}

private fun EventDto.toDomain(): Event {
    return Event(
        id = this.id,
        name = this.name,
        category = this.category,
        location = this.location,
        date = this.date,
        time = this.time,
        price = this.price,
        description = this.description,
        duration = this.duration,
        liked = this.liked,
        eventImage = this.eventImage,
        eventWeb = this.eventWeb
    )
}
