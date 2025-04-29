package com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.Date
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Local

data class UserDto(
    val id: Long,
    val name: String,
    val email: String,
    val firebaseUid: String,
    val registrationDate: String
)