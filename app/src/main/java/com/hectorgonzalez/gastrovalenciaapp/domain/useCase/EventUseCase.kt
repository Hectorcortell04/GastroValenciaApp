package com.hectorgonzalez.gastrovalenciaapp.domain.useCase

import com.hectorgonzalez.gastrovalenciaapp.data.repository.EventRepository
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event

class EventsUseCase(private val repository: EventRepository = EventRepository()) {
    suspend fun getEvents(): List<Event> = repository.getEvents()
}