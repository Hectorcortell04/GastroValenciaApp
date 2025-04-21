package com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.api

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import retrofit2.http.GET

interface EventApi {
    @GET("/events")
    suspend fun getEvents(): List<EventDto>
}