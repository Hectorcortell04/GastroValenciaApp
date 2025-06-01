package com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.api

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface EventApi {
    @GET("/events")
    suspend fun getEvents(): List<EventDto>

    @GET("/events/search")
    suspend fun getEventsByName(@Query("name") name: String): List<EventDto>

    @GET("/events/{id}")
    suspend fun getEventById(@Path("id") id: String): EventDto

    @POST("/event_likes/{eventId}/like/{userId}")
    suspend fun likeEvents(@Path("eventId") eventId: String, @Path("userId") userId: String)
}