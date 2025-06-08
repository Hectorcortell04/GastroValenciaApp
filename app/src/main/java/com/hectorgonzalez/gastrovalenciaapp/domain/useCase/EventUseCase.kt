package com.hectorgonzalez.gastrovalenciaapp.domain.useCase

import com.hectorgonzalez.gastrovalenciaapp.data.repository.EventRepository
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event

class EventsUseCase(private val repository: EventRepository = EventRepository()) {
    suspend fun getEvents(userId:String): List<Event> = repository.getEvents(userId)
    suspend fun getEventsByName(name: String): List<Event> = repository.getEventsByName(name)
    suspend fun getEventById(eventId: String, userId: String): Event = repository.getEventById(eventId,userId)
    suspend fun toggleLike(eventId:String, userId:String) : Unit = repository.likeEvent(eventId,userId)
    suspend fun listEventsLikes(userId:String) : List<Event> = repository.listEventsLikes(userId)
}