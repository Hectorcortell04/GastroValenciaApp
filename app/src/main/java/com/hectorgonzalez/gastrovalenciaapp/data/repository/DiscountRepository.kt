package com.hectorgonzalez.gastrovalenciaapp.data.repository

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.discounts.DiscountDataSource
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.discounts.dto.DiscountDto
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Discount

class DiscountRepository(
    private val remoteDataSource: DiscountDataSource = DiscountDataSource()
) {
    suspend fun getDiscounts(userId: String): Discount {
        return remoteDataSource.getDiscounts(userId).toDomain()
    }
}

//Aquí mapeamos de el objeto de la base de datos (dto) a nuestra entidad local.
//En este caso desde back el objeto viene con todos los campos correctos y no tenemos que implementar
// lógica de parseo específica

fun DiscountDto.toDomain(): Discount =
    Discount(
       id = this.id,
       userId = this.userId,
       membershipLevelId = this.membershipLevelId,
       discounts = this.discounts
    )
