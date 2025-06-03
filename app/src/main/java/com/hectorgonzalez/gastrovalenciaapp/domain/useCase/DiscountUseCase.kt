package com.hectorgonzalez.gastrovalenciaapp.domain.useCase

import com.hectorgonzalez.gastrovalenciaapp.data.repository.DiscountRepository
import com.hectorgonzalez.gastrovalenciaapp.data.repository.EventRepository
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Discount
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event

class DiscountUseCase(private val repository: DiscountRepository = DiscountRepository()) {
    suspend fun getDiscounts(userId: String): Discount = repository.getDiscounts(userId)
}