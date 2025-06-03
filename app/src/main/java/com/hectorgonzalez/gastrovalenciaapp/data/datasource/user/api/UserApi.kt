package com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.api

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto.RegisterUserDto
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto.UserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @GET("/users")
    suspend fun getUsers(): List<UserDto>

    @GET("/users/firebase/{uid}")
    suspend fun getUserId(@Path("uid") id: String): UserDto

    @POST("/users/register")
    suspend fun registerUser(@Body userDto: RegisterUserDto): UserDto
}