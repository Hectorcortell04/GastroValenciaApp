package com.hectorgonzalez.gastrovalenciaapp.data.datasource.user

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.api.UserApi
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto.UserDto
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.retrofitClient.RetrofitClient

class UserDataSource(
    private val api: UserApi = RetrofitClient.api
) {
    suspend fun fetchUsers(): List<UserDto> =
        api.getUsers()
}