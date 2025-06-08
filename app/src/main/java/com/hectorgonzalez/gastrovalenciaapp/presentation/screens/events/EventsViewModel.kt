package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.events

import android.content.Context
import androidx.compose.runtime.*
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
                // ✅ ACTUALIZACIÓN OPTIMISTA: Cambiar la UI INMEDIATAMENTE
                events = events.map { event ->
                    if (event.id == eventId) {
                        event.copy(liked = newLikedState)
                    } else {
                        event
                    }
                }

                // Ahora hacer la llamada al servidor
                eventsUseCase.toggleLike(eventId.toString(), userId.toString())

                // Si llegamos aquí, la operación fue exitosa
                // La UI ya está actualizada con el estado correcto

            } catch (e: Exception) {
                // ❌ ERROR: Revertir el cambio en la UI
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

    /**
     * Versión alternativa más segura que actualiza desde el servidor
     * Úsala solo si la versión optimista te da problemas
     */
    fun toggleEventLikeSafe(eventId: Int, context: Context) {
        val userId = UserManager.getUserId(context)

        if (userId == null) {
            errorMessage = "Usuario no encontrado. Por favor, inicia sesión nuevamente."
            return
        }

        viewModelScope.launch {
            isLikingInProgress = true

            try {
                // Hacer la llamada al servidor sin actualización optimista
                eventsUseCase.toggleLike(eventId.toString(), userId.toString())

                // Obtener el evento actualizado desde el servidor
                val updatedEvent = eventsUseCase.getEventById(eventId.toString(), userId.toString())

                events = events.map { event ->
                    if (event.id == eventId) {
                        updatedEvent
                    } else {
                        event
                    }
                }

            } catch (e: Exception) {
                errorMessage = "Error al actualizar favorito: ${e.localizedMessage}"
            } finally {
                isLikingInProgress = false
            }
        }
    }

    fun clearError() {
        errorMessage = null
    }

    fun retryFetchEvents(context: Context) {
        fetchEvents(context)
    }
}