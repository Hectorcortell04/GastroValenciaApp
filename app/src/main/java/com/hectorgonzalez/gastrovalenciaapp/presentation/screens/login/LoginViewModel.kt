package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.login

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.hectorgonzalez.gastrovalenciaapp.domain.useCase.UserUseCase
import com.hectorgonzalez.gastrovalenciaapp.utils.UserManager
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel(
    private val userUseCase: UserUseCase = UserUseCase() // Asume que tienes este UseCase
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun login(
        email: String,
        password: String,
        context: Context,
        onSuccess: () -> Unit
    ) {
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Por favor, completa todos los campos"
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                // 1. Autenticar con Firebase
                val authResult = FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, password)
                    .await()

                val firebaseUser = authResult.user
                if (firebaseUser != null) {
                    // 2. Obtener datos completos del usuario desde tu API/base de datos
                    val userData = userUseCase.getUserId(firebaseUser.uid)

                    if (userData != null) {
                        // 3. Guardar el userId localmente
                        UserManager.saveUserId(context, userData.id)

                        // 4. Login exitoso
                        onSuccess()
                    } else {
                        errorMessage = "No se pudieron obtener los datos del usuario"
                        FirebaseAuth.getInstance().signOut() // Cerrar sesión si no hay datos
                    }
                } else {
                    errorMessage = "Error de autenticación"
                }

            } catch (e: Exception) {
                // Manejar errores específicos de Firebase
                errorMessage = when (e.message) {
                    "The email address is badly formatted." -> "El formato del correo electrónico no es válido"
                    "There is no user record corresponding to this identifier. The user may have been deleted." -> "No existe una cuenta con este correo electrónico"
                    "The password is invalid or the user does not have a password." -> "La contraseña es incorrecta"
                    "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> "Error de conexión. Verifica tu internet"
                    else -> "Error al iniciar sesión. Verifica tus credenciales"
                }
            } finally {
                isLoading = false
            }
        }
    }

    fun clearError() {
        errorMessage = null
    }
}