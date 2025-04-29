package com.hectorgonzalez.gastrovalenciaapp.data.repository

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.UserDataSource
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto.UserDto
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.User
import java.time.LocalDateTime

class UserRepository(
    private val remoteDataSource: UserDataSource = UserDataSource()
) {
    suspend fun getAllUsers(): List<User> =
        remoteDataSource.fetchUsers().map { it.toUser() }
}

fun UserDto.toUser(): User =
    User(
        id = this.id,
        name = this.name,
        email = this.email,
        firebaseUid = this.firebaseUid,
        registrationDate = LocalDateTime.parse(this.registrationDate)
    )