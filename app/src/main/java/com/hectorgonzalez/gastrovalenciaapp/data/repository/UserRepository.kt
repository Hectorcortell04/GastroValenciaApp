package com.hectorgonzalez.gastrovalenciaapp.data.repository

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.UserDataSource
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto.RegisterUserDto
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto.UserDto
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.User
import java.time.LocalDateTime
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto.RegisterRequest


class UserRepository(
    private val remoteDataSource: UserDataSource = UserDataSource()
) {
    suspend fun getAllUsers(): List<User> =
        remoteDataSource.fetchUsers().map { it.toUser() }

    suspend fun getUserId(uid: String): User =
        remoteDataSource.getUserId(uid).toUser()

//    suspend fun registerUser(registerData: RegisterUserDto) {
//        remoteDataSource.registerUser(registerData)
//    }

    suspend fun registerUser(registerData: RegisterRequest, token: String) {
        remoteDataSource.registerUser(registerData, token)
    }
}

fun UserDto.toUser(): User =
    User(
        id = this.id,
        name = this.name,
        email = this.email,
        firebaseUid = this.firebaseUid,
        registrationDate = LocalDateTime.parse(this.registrationDate),
        userImage = this.userImage
    )