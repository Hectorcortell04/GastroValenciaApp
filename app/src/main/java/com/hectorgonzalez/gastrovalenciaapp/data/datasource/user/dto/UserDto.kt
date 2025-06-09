package com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto

data class UserDto(
    val id: Int,
    val name: String,
    val email: String,
    val firebaseUid: String,
    val registrationDate: String,
    val userImage: String
)