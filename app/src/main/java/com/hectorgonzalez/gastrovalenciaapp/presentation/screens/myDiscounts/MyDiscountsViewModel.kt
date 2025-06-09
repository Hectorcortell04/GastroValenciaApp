package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.myDiscounts


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Discount
import com.hectorgonzalez.gastrovalenciaapp.domain.useCase.DiscountUseCase
import kotlinx.coroutines.launch

class MyDiscountsViewModel(
    private val discountUseCase: DiscountUseCase = DiscountUseCase()
) : ViewModel() {

    // Aquí se guarda el descuento que se obtiene del backend.
    var discount by mutableStateOf<Discount?>(null)
        private set

    // Flag para saber si se está cargando el descuento
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Función que hace la llamada al caso de uso
    fun fetchDiscount(userId: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                discount = discountUseCase.getDiscounts(userId)
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error desconocido"
            } finally {
                isLoading = false
            }
        }
    }

    // Función auxiliar para borrar el mensaje de error
    fun clearError() {
        errorMessage = null
    }
}