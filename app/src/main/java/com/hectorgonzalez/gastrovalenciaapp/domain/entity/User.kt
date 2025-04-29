package com.hectorgonzalez.gastrovalenciaapp.domain.entity

import java.time.LocalDateTime

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val firebaseUid: String,
    val registrationDate: LocalDateTime
)