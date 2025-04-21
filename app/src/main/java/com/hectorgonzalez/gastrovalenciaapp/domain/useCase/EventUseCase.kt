package com.hectorgonzalez.gastrovalenciaapp.domain.useCase

import com.hectorgonzalez.gastrovalenciaapp.data.repository.EventRepository
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event

class GetEventsUseCase(private val repository: EventRepository) {
    suspend operator fun invoke(): List<Event> = repository.getEvents()
}