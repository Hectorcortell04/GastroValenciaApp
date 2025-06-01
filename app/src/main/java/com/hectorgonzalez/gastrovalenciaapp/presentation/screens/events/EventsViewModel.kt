package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.events

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event
import com.hectorgonzalez.gastrovalenciaapp.domain.useCase.EventsUseCase
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

    init {
        fetchEvents()
    }

    private fun fetchEvents() {
        viewModelScope.launch {
            isLoading = true
            try {
                events = eventsUseCase.getEvents()
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
}
