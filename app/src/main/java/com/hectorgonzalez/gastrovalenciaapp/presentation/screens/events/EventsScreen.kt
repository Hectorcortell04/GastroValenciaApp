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
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.components.EventCard
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.login.LoginViewModel
import com.hectorgonzalez.gastrovalenciaapp.presentation.viewmodel.EventViewModel

@Composable
fun EventsScreen(
    viewModel: EventViewModel = viewModel()
) {
    var searchText by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // 🔍 Buscador
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text("Buscar") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 📋 Lista de eventos (dummy por ahora)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(5) {
                EventCard(
                    imageUrl = "https://media.istockphoto.com/id/1389348844/es/foto/foto-de-estudio-de-una-hermosa-joven-sonriendo-mientras-est%C3%A1-de-pie-sobre-un-fondo-gris.jpg?s=2048x2048&w=is&k=20&c=VZNaxuI_YA8ikuMh_0BH75LVYggIYrAjkVjBpP0dIxs=", // imagen aleatoria  https://picsum.photos/800/400?random=$it
                    rating = "4,0",
                    timeAgo = "Hace 3 horas",
                    description = "El menú de Margarito es una auténtica celebración de los sabores mediterráneos...",
                    authorName = "Susana Monzó",
                    authorDesc = "Restaurante Margarito",
                    likes = 124,
                    comments = 20
                )
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