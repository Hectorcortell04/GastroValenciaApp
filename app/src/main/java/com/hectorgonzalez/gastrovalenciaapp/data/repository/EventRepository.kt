package com.hectorgonzalez.gastrovalenciaapp.data.repository

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.api.EventApi
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event

class EventRepository(private val api: EventApi)  {
     suspend fun getEvents(): List<Event> {
        return api.getEvents().map { it.toDomain() }
    }
}