package com.hectorgonzalez.gastrovalenciaapp.data.datasource.event

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.api.EventApi
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import com.hectorgonzalez.gastrovalenciaapp.data.networkClient.NetworkClient

class EventDataSource {
    private val eventApi = NetworkClient.instance.create(EventApi::class.java)

    suspend fun getAllEvents(): List<EventDto> {
        return eventApi.getEvents()
    }
}