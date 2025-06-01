package com.hectorgonzalez.gastrovalenciaapp.domain.useCase

import com.hectorgonzalez.gastrovalenciaapp.data.repository.EventRepository
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event

class EventsUseCase(private val repository: EventRepository = EventRepository()) {
    suspend fun getEvents(): List<Event> = repository.getEvents()
    suspend fun getEventsByName(name: String): List<Event> = repository.getEventsByName(name)
    suspend fun getEventById(id: String): Event = repository.getEventById(id)
    suspend fun likeEvent(eventId:String,userId:String) : Unit = repository.likeEvent(eventId,userId)
}