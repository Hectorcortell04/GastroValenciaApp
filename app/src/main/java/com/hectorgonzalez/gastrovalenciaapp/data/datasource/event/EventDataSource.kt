package com.hectorgonzalez.gastrovalenciaapp.data.datasource.event

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.api.EventApi
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import com.hectorgonzalez.gastrovalenciaapp.data.networkClient.NetworkClient

class EventDataSource {
    private val eventApi = NetworkClient.instance.create(EventApi::class.java)

    suspend fun getAllEvents(): List<EventDto> {
        return eventApi.getEvents()
    }

    suspend fun getEventsByName(name: String): List<EventDto> {
        return eventApi.getEventsByName(name)
    }

    suspend fun getEventById(id: String): EventDto {
        return eventApi.getEventById(id)
    }

    suspend fun likeEvent(eventId:String,userId:String){
        return eventApi.likeEvents(eventId,userId)
    }
}