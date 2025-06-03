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

    var discount by mutableStateOf<Discount?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

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

    fun clearError() {
        errorMessage = null
    }
}