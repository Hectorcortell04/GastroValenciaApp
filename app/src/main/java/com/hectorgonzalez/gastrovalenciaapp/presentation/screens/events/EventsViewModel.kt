package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.events

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event
import com.hectorgonzalez.gastrovalenciaapp.domain.useCase.EventsUseCase
import com.hectorgonzalez.gastrovalenciaapp.utils.UserManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EventViewModel(
    private val eventsUseCase: EventsUseCase = EventsUseCase()
) : ViewModel() {
    private var searchJob: Job? = null

    var events by mutableStateOf<List<Event>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isLikingInProgress by mutableStateOf(false)
        private set

    // Carga inicial de eventos (se llama al entrar en la pantalla)
    fun fetchEvents(context: Context) {
        val userId = UserManager.getUserId(context)

        if (userId == null) {
            errorMessage = "Usuario no encontrado. Por favor, inicia sesión nuevamente."
            return
        }

        viewModelScope.launch {
            isLoading = true
            try {
                events = eventsUseCase.getEvents(userId.toString())
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error desconocido"
            } finally {
                isLoading = false
            }
        }
    }

    // Buscar eventos por nombre con un pequeño retraso (debounce)
    fun searchEventsByName(name: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            isLoading = true
            try {
                events = eventsUseCase.getEventsByName(name)
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error desconocido"
            } finally {
                isLoading = false
            }
        }
    }

    // Marcar o desmarcar like en un evento
    fun toggleEventLike(eventId: Int, context: Context) {
        val userId = UserManager.getUserId(context)

        if (userId == null) {
            errorMessage = "Usuario no encontrado. Por favor, inicia sesión nuevamente."
            return
        }

        viewModelScope.launch {
            isLikingInProgress = true

            // Encontrar el evento en la lista actual
            val currentEvent = events.find { it.id == eventId }
            if (currentEvent == null) {
                errorMessage = "Evento no encontrado"
                isLikingInProgress = false
                return@launch
            }

            // Guardar el estado anterior para poder revertir en caso de error
            val previousLikedState = currentEvent.liked
            val newLikedState = !previousLikedState

            try {
                events = events.map { event ->
                    if (event.id == eventId) {
                        event.copy(liked = newLikedState)
                    } else {
                        event
                    }
                }

                // Ahora hace la llamada al servidor
                eventsUseCase.toggleLike(eventId.toString(), userId.toString())

            } catch (e: Exception) {
                events = events.map { event ->
                    if (event.id == eventId) {
                        event.copy(liked = previousLikedState)
                    } else {
                        event
                    }
                }
                errorMessage = "Error al actualizar favorito: ${e.localizedMessage}"
            } finally {
                isLikingInProgress = false
            }
        }
    }

    // Limpia el mensaje de error para que no se repita en pantalla
    fun clearError() {
        errorMessage = null
    }
}