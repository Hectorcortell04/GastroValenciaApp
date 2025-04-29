package com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.api

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto.UserDto
import retrofit2.http.GET

interface UserApi {
    @GET("/users")
    suspend fun getUsers(): List<UserDto>
}