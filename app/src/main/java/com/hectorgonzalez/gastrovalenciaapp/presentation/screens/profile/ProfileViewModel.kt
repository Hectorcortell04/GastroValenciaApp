package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.User
import com.hectorgonzalez.gastrovalenciaapp.domain.useCase.UsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val usersUseCase: UsersUseCase = UsersUseCase()
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadUser(firebaseUid: String, context: Context) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = usersUseCase.getUserId(firebaseUid)
                _user.value = result

                // Guardar el ID del usuario en SharedPreferences
                result?.let { user ->
                    saveUserId(context, user.id)
                }
            } catch (e: Exception) {
                _user.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun saveUserId(context: Context, userId: Int) {
        val sharedPrefs = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
        sharedPrefs.edit()
            .putInt(USER_ID_KEY, userId)
            .apply()
    }

    fun clearUserData(context: Context) {
        _user.value = null
        val sharedPrefs = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
        sharedPrefs.edit()
            .remove(USER_ID_KEY)
            .apply()
    }

    companion object {
        const val USER_PREFERENCES = "user_preferences"
        const val USER_ID_KEY = "user_id"
    }
}