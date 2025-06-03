package com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto

data class RegisterUserDto(
    val name: String,
    val email: String,
    val firebaseUid: String,
    val userImage: String,
)
