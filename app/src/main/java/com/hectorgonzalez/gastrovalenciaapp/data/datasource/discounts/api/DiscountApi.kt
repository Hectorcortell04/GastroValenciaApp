package com.hectorgonzalez.gastrovalenciaapp.data.datasource.discounts.api

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.discounts.dto.DiscountDto
import retrofit2.http.GET
import retrofit2.http.Path

// Devuelve el descuento asociado a un usuario por su ID
interface DiscountApi {
    @GET("/discounts/user/{id}")
    suspend fun getUserDiscounts(@Path("id") id: String): DiscountDto
}