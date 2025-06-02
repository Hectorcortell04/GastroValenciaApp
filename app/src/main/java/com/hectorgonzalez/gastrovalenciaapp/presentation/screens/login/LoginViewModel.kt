package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.User
import com.hectorgonzalez.gastrovalenciaapp.domain.useCase.UsersUseCase
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val usersUseCase = UsersUseCase()

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadUsers()
    }

     private fun loadUsers() {
        viewModelScope.launch {
            try {
                val list = usersUseCase.getUsers()
                _users.postValue(list)
            } catch (e: Exception) {
                _error.postValue(e.localizedMessage ?: "Error desconocido")
            }
        }
    }
}