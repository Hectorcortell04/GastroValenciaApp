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

    /**
     * Carga un evento específico por su ID
     */
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

            // Guardamos el estado actual por si hay error
            val previousLikedState = currentEvent.liked
            val newLikedState = !previousLikedState // 🔥 CALCULAR EL NUEVO ESTADO

            try {
                // ✅ CORRECCIÓN: Actualizamos optimísticamente la UI con el estado OPUESTO
                event = currentEvent.copy(liked = newLikedState)

                // Llamamos al servidor
                eventsUseCase.toggleLike(currentEvent.id.toString(), userId.toString())

                // Si llegamos aquí, la operación fue exitosa
                // El estado ya está actualizado optimísticamente con el valor correcto

            } catch (e: Exception) {
                // En caso de error, revertimos al estado anterior
                event = currentEvent.copy(liked = previousLikedState)
                errorMessage = "Error al actualizar like: ${e.localizedMessage}"
            } finally {
                isLikingInProgress = false
            }
        }
    }

    /**
     * Versión alternativa más segura (sin actualización optimista)
     * Úsala si sigues teniendo problemas con la versión optimista
     */
    fun toggleLikeSafe(context: Context) {
        val currentEvent = event ?: return
        val userId = UserManager.getUserId(context)

        if (userId == null) {
            errorMessage = "Error: Usuario no encontrado. Por favor, inicia sesión nuevamente."
            return
        }

        viewModelScope.launch {
            isLikingInProgress = true

            try {
                // Llamar al servidor SIN actualización optimista
                eventsUseCase.toggleLike(currentEvent.id.toString(), userId.toString())

                // Obtener el estado real desde el servidor
                val updatedEvent = eventsUseCase.getEventById(
                    currentEvent.id.toString(),
                    userId.toString()
                )
                event = updatedEvent

            } catch (e: Exception) {
                errorMessage = "Error al actualizar like: ${e.localizedMessage}"
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
     * Limpia el estado del ViewModel
     */
    override fun onCleared() {
        super.onCleared()
        event = null
        errorMessage = null
        isLoading = false
        isLikingInProgress = false
    }
}