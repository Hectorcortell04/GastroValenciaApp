package com.hectorgonzalez.gastrovalenciaapp.domain.useCase

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto.RegisterRequest
import com.hectorgonzalez.gastrovalenciaapp.data.repository.UserRepository
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.User

// UseCase encargado de obtener los datos de los usuarios. Se comunica con el repositorio
class UserUseCase(
    private val repository: UserRepository = UserRepository()
) {
    suspend fun getUsers(): List<User> =
        repository.getAllUsers()

    suspend fun getUserId(uid: String) =
        repository.getUserId(uid)

    suspend fun registerUser(request: RegisterRequest, token: String) {
        repository.registerUser(request, token)
    }
}