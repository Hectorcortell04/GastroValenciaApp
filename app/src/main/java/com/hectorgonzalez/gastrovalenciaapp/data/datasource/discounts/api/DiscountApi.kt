package com.hectorgonzalez.gastrovalenciaapp.data.datasource.discounts.api

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.discounts.dto.DiscountDto
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DiscountApi {
    @GET("/discounts/user/{id}")
    suspend fun getUserDiscounts(@Path("id") id: String): DiscountDto
}