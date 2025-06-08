package com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val userImage: String?
)
