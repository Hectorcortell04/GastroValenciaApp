package com.hectorgonzalez.gastrovalenciaapp.domain.useCase

import com.hectorgonzalez.gastrovalenciaapp.data.repository.UserRepository
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.User

class UsersUseCase(
    private val repository: UserRepository = UserRepository()
) {
    suspend fun getUsers(): List<User> =
        repository.getAllUsers()

    suspend fun getUserId(uid:String) =
        repository.getUserId(uid)
}