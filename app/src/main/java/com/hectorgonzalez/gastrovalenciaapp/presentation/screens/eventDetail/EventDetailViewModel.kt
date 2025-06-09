import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event
import com.hectorgonzalez.gastrovalenciaapp.domain.useCase.EventsUseCase
import com.hectorgonzalez.gastrovalenciaapp.utils.UserManager
import kotlinx.coroutines.launch

class EventDetailViewModel(
    private val eventsUseCase: EventsUseCase = EventsUseCase()
) : ViewModel() {

    // Estado del evento actual
    var event by mutableStateOf<Event?>(null)
        private set

    // Estado de carga
    var isLoading by mutableStateOf(false)
        private set

    // Estado de error
    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Estado de carga para el like (para mostrar loading en el botón)
    var isLikingInProgress by mutableStateOf(false)
        private set

    // Carga un evento por ID
    fun loadEvent(eventId: Int, context: Context) {
        val userId = UserManager.getUserId(context)

        if (userId == null) {
            errorMessage = "Usuario no encontrado. Por favor, inicia sesión nuevamente."
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val loadedEvent = eventsUseCase.getEventById(
                    eventId.toString(),
                    userId = userId.toString()
                )
                event = loadedEvent
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error desconocido al cargar el evento"
                event = null
            } finally {
                isLoading = false
            }
        }
    }


    fun toggleLike(context: Context) {
        val currentEvent = event ?: return
        val userId = UserManager.getUserId(context)

        if (userId == null) {
            errorMessage = "Error: Usuario no encontrado. Por favor, inicia sesión nuevamente."
            return
        }

        viewModelScope.launch {
            isLikingInProgress = true

            val previousLikedState = currentEvent.liked
            val newLikedState = !previousLikedState

            try {
                event = currentEvent.copy(liked = newLikedState)

                eventsUseCase.toggleLike(currentEvent.id.toString(), userId.toString())


            } catch (e: Exception) {
                event = currentEvent.copy(liked = previousLikedState)
                errorMessage = "Error al actualizar like: ${e.localizedMessage}"
            } finally {
                isLikingInProgress = false
            }
        }
    }

    fun retryLoadEvent(context: Context) {
        val currentEvent = event
        if (currentEvent != null) {
            loadEvent(currentEvent.id, context)
        }
    }


    fun clearError() {
        errorMessage = null
    }

    override fun onCleared() {
        super.onCleared()
        event = null
        errorMessage = null
        isLoading = false
        isLikingInProgress = false
    }
}