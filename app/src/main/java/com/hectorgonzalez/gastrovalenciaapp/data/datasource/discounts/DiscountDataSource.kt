package com.hectorgonzalez.gastrovalenciaapp.data.datasource.discounts

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.discounts.api.DiscountApi
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.discounts.dto.DiscountDto
import com.hectorgonzalez.gastrovalenciaapp.data.networkClient.NetworkClient

class DiscountDataSource {
    private val discountApi = NetworkClient.instance.create(DiscountApi::class.java)

    // DataSource que se encarga de hacer la llamada a la API para obtener los descuentos del usuario
    suspend fun getDiscounts(userId: String): DiscountDto {
        return discountApi.getUserDiscounts(userId)
    }

}