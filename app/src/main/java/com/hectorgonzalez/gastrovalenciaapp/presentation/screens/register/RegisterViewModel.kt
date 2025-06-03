package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto.RegisterUserDto
import com.hectorgonzalez.gastrovalenciaapp.domain.useCase.UserUseCase
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val userUseCase: UserUseCase = UserUseCase()
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isRegistrationSuccessful by mutableStateOf(false)
        private set

    fun registerUser(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit = {}
    ) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                // Aquí normalmente crearías el usuario en Firebase Authentication
                // y obtendrías el firebaseUid
                val firebaseUid = createFirebaseUser(email, password)

                val registerData = RegisterUserDto(
                    name = name,
                    email = email,
                    firebaseUid = firebaseUid,
                    userImage = "https://res.cloudinary.com/dpgda2bnc/image/upload/v1746443716/Perfil_5_vjnpem.png"
                )

                userUseCase.registerUser(registerData)
                isRegistrationSuccessful = true
                onSuccess()

            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error durante el registro"
            } finally {
                isLoading = false
            }
        }
    }

    // Función simulada para crear usuario en Firebase
    // En una implementación real, aquí usarías Firebase Auth
    private fun createFirebaseUser(email: String, password: String): String {
        // TODO: Implementar Firebase Authentication
        //FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
        // Por ahora retornamos un UID simulado
        return "hQfpiW2F15czQQ0i1RRB4diqUCi2"
    }

    fun clearError() {
        errorMessage = null
    }

    fun resetRegistrationState() {
        isRegistrationSuccessful = false
        errorMessage = null
    }
}