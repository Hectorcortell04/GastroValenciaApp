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

    // Estado de favorito/like
    var isLiked by mutableStateOf(false)
        private set

    // Estado de carga para el like (para mostrar loading en el botón)
    var isLikingInProgress by mutableStateOf(false)
        private set

    /**
     * Carga un evento específico por su ID
     */
    fun loadEvent(eventId: Int, context: Context) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val loadedEvent = eventsUseCase.getEventById(eventId.toString())
                event = loadedEvent

                // Verificar si el evento está marcado como favorito/liked
                checkIfEventIsLiked(eventId, context)

            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error desconocido al cargar el evento"
                event = null
            } finally {
                isLoading = false
            }
        }
    }

    /**
     * Verifica si el evento actual está marcado como favorito
     * Nota: Aquí asumo que tienes un método para verificar si está liked.
     * Si no lo tienes, puedes omitir esta verificación o implementarla según tu lógica.
     */
    private suspend fun checkIfEventIsLiked(eventId: Int, context: Context) {
        try {
            val userId = UserManager.getUserId(context)
            if (userId != null) {
                // Si tienes un método para verificar si está liked:
                // isLiked = eventsUseCase.isEventLiked(eventId.toString(), userId.toString())

                // Si no tienes el método, por ahora lo dejamos en false
                isLiked = false
            }
        } catch (e: Exception) {
            // Si hay error al verificar likes, simplemente no marcamos como liked
            isLiked = false
        }
    }

    /**
     * Alterna el estado de like del evento actual
     * El método likeEvent maneja automáticamente like/unlike
     */
    fun toggleLike(context: Context) {
        val currentEvent = event ?: return
        val userId = UserManager.getUserId(context)

        if (userId == null) {
            errorMessage = "Error: Usuario no encontrado. Por favor, inicia sesión nuevamente."
            return
        }

        viewModelScope.launch {
            isLikingInProgress = true
            try {
                eventsUseCase.likeEvent(currentEvent.id.toString(), userId.toString())
                isLiked = !isLiked

            } catch (e: Exception) {
                errorMessage = "Error al actualizar like: ${e.localizedMessage}"
                // No cambiamos isLiked para mantener el estado anterior en caso de error
            } finally {
                isLikingInProgress = false
            }
        }
    }

    /**
     * Reintenta la carga del evento en caso de error
     */
    fun retryLoadEvent(context: Context) {
        val currentEvent = event
        if (currentEvent != null) {
            loadEvent(currentEvent.id, context)
        }
    }

    /**
     * Limpia el mensaje de error
     */
    fun clearError() {
        errorMessage = null
    }

    /**
     * Maneja la acción de reservar (placeholder para futura implementación)
     */
    fun onReserveClick() {
        // TODO: Implementar lógica de reserva
        // Ejemplo: navegar a web externa, abrir formulario de reserva, etc.
    }

    /**
     * Maneja la acción de compartir evento
     */
    fun shareEvent() {
        val currentEvent = event ?: return

        // TODO: Implementar lógica para compartir evento
        // Ejemplo: crear intent de compartir con información del evento
    }

    /**
     * Limpia el estado del ViewModel
     */
    override fun onCleared() {
        super.onCleared()
        event = null
        errorMessage = null
        isLoading = false
        isLiked = false
        isLikingInProgress = false
    }
}