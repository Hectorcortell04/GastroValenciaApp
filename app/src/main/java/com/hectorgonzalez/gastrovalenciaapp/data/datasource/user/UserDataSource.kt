package com.hectorgonzalez.gastrovalenciaapp.data.datasource.user

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.api.UserApi
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto.RegisterRequest
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto.UserDto
import com.hectorgonzalez.gastrovalenciaapp.data.networkClient.NetworkClient

// DataSource que se encarga de conectar con la API de users
class UserDataSource {
    private val userApi = NetworkClient.instance.create(UserApi::class.java)

    suspend fun fetchUsers(): List<UserDto> =
        userApi.getUsers()

    suspend fun getUserId(uid: String): UserDto =
        userApi.getUserId(uid)

    suspend fun registerUser(request: RegisterRequest, token: String): UserDto {
        return userApi.registerUser(request, "Bearer $token")
    }
}