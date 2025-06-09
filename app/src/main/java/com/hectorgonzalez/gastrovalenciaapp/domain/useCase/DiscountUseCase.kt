package com.hectorgonzalez.gastrovalenciaapp.domain.useCase

import com.hectorgonzalez.gastrovalenciaapp.data.repository.DiscountRepository
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Discount

// UseCase encargado de obtener los descuentos del usuario. Se comunica con el repositorio
class DiscountUseCase(private val repository: DiscountRepository = DiscountRepository()) {
    suspend fun getDiscounts(userId: String): Discount = repository.getDiscounts(userId)
}