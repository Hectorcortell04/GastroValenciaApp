package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.dto.EventDto
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.components.EventCard
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.login.LoginViewModel
import com.hectorgonzalez.gastrovalenciaapp.presentation.viewmodel.EventViewModel

@Composable
fun EventsScreen(
    viewModel: EventViewModel = viewModel()
) {
    var searchText by remember { mutableStateOf("") }
    val events = viewModel.events
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
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
                        imageUrl = "https://res.cloudinary.com/dpgda2bnc/image/upload/v1746443722/Perfil_1_cyk1iy.png=${event.id}",
                        isLiked = false,
                        onLikeClick = {}
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun EventsScreenPreview() {
    com.hectorgonzalez.gastrovalenciaapp.ui.theme.GastroValenciaAppTheme {
        EventsScreen()
    }
}