package com.hectorgonzalez.gastrovalenciaapp.data.datasource.event

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.api.EventApi
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import com.hectorgonzalez.gastrovalenciaapp.data.networkClient.NetworkClient

// DataSource que se encarga de conectar con la API de eventos
class EventDataSource {
    private val eventApi = NetworkClient.instance.create(EventApi::class.java)

    suspend fun getAllEvents(userId: String): List<EventDto> {
        return eventApi.getEvents(userId)
    }

    suspend fun getEventsByName(name: String): List<EventDto> {
        return eventApi.getEventsByName(name)
    }

    suspend fun getEventById(eventId: String, userId: String): EventDto {
        return eventApi.getEventById(eventId, userId)
    }

    suspend fun likeEvent(eventId: String, userId: String) {
        return eventApi.likeEvents(eventId, userId)
    }

    suspend fun listEventsLikes(userId: String): List<EventDto> {
        return eventApi.listEventsLikes(userId)
    }
}