package com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto

// El DTO para hacer el register con el campo password
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val userImage: String?
)
