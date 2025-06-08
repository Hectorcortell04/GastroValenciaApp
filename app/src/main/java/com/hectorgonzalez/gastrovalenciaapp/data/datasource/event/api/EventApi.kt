package com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.api

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.restaurant.dto.RestaurantDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface EventApi {
    @GET("/events/user/{userId}")
    suspend fun getEvents(@Path("userId") userId: String): List<EventDto>

    @GET("/events/search")
    suspend fun getEventsByName(@Query("name") name: String): List<EventDto>

    @GET("/events/{eventId}/{userId}")
    suspend fun getEventById(@Path("eventId") eventId: String,@Path("userId") userId: String): EventDto

    @POST("/event_likes/{eventId}/like/{userId}")
    suspend fun likeEvents(@Path("eventId") eventId: String, @Path("userId") userId: String)

    @GET("/event_likes/user/{userId}")
    suspend fun listEventsLikes(@Path("userId") userId: String) : List<EventDto>
}