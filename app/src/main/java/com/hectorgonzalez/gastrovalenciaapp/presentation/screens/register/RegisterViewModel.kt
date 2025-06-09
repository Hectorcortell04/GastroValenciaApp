package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorgonzalez.gastrovalenciaapp.domain.useCase.UserUseCase
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuth
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.dto.RegisterRequest
import kotlinx.coroutines.tasks.await


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
        imageUrl: String = "",
        onSuccess: () -> Unit = {}
    ) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                // Aquí normalmente crearías el usuario en Firebase Authentication
                // y obtendrías el firebaseUid
                val idToken = createFirebaseUser(email, password)

                // Si no se proporciona URL de imagen, usar la imagen por defecto
                val finalImageUrl = imageUrl.ifBlank {
                    "https://res.cloudinary.com/dpgda2bnc/image/upload/v1746443716/Perfil_5_vjnpem.png"
                }

                val registerData = RegisterRequest(
                    name = name,
                    email = email,
                    password = password,
                    userImage = finalImageUrl
                )


                userUseCase.registerUser(registerData, idToken)
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
    private suspend fun createFirebaseUser(email: String, password: String): String {
        val authResult = FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .await()

        val firebaseUser = authResult.user
            ?: throw Exception("No se pudo registrar en Firebase")

        val tokenResult = firebaseUser.getIdToken(true).await()
        return tokenResult.token ?: throw Exception("No se pudo obtener el token de Firebase")
    }


    fun clearError() {
        errorMessage = null
    }

    fun resetRegistrationState() {
        isRegistrationSuccessful = false
        errorMessage = null
    }
}