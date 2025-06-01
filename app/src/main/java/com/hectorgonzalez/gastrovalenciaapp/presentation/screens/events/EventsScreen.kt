package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun EventsScreen(
    navigateToEventDetail: (Int) -> Unit, // Cambiar para recibir ID
    viewModel: EventViewModel = viewModel()
) {
    var searchText by remember { mutableStateOf("") }
    val events = viewModel.events
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                viewModel.searchEventsByName(it)
            },
            placeholder = { Text("Buscar") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Text("Cargando eventos...")
        } else if (error != null) {
            Text("Error: $error")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(events.size) { index ->
                    val event = events[index]
                    EventCard(
                        event = event,
                        imageUrl = "https://www.viaempresa.cat/uploads/s1/25/84/04/94/paelles-valencia.jpeg",
                        isLiked = false,
                        onLikeClick = {},
                        onCardClick = {
                            navigateToEventDetail(event.id) // Pasar el ID del evento
                        }
                    )
                }
            }
        }
    }
}