package com.hectorgonzalez.gastrovalenciaapp.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Event
import com.hectorgonzalez.gastrovalenciaapp.domain.entity.Restaurant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen() {
    // Mock data - será reemplazado por ViewModel
    val events = listOf(
        Event(
            id = 1,
            name = "Festival Gastronómico Valencia",
            category = "Gastronómico",
            location = "Ciudad de las Artes",
            date = "15/06/2025",
            time = "18:00",
            price = 25.0,
            description = "Festival con los mejores chefs de Valencia",
            duration = "4 horas"
        ),
        Event(
            id = 2,
            name = "Taller de Paella Tradicional",
            category = "Taller",
            location = "Mercado Central",
            date = "20/06/2025",
            time = "10:00",
            price = 45.0,
            description = "Aprende a hacer paella auténtica valenciana",
            duration = "3 horas"
        ),
        Event(
            id = 3,
            name = "Cata de Vinos Valencianos",
            category = "Cata",
            location = "Bodega El Raco",
            date = "25/06/2025",
            time = "19:30",
            price = 0.0,
            description = "Degustación gratuita de vinos locales",
            duration = "2 horas"
        )
    )

    val restaurants = listOf(
        Restaurant(
            id = 1L,
            name = "Casa Roberto",
            foodType = "Cocina Valenciana",
            address = "Calle de la Paz, 15",
            rating = 4.5,
            averagePrice = 35.0,
            restaurantImages = listOf("https://example.com/casa-roberto.jpg"),
            menuImage = "https://example.com/menu-casa-roberto.jpg",
            description = "Auténtica cocina valenciana desde 1920"
        ),
        Restaurant(
            id = 2L,
            name = "El Jardín Secreto",
            foodType = "Mediterránea",
            address = "Plaza del Ayuntamiento, 8",
            rating = 4.8,
            averagePrice = 55.0,
            restaurantImages = listOf("https://example.com/jardin-secreto.jpg"),
            menuImage = "https://example.com/menu-jardin.jpg",
            description = "Cocina mediterránea con toques modernos"
        ),
        Restaurant(
            id = 3L,
            name = "Tapas & Más",
            foodType = "Tapas",
            address = "Barrio del Carmen, 22",
            rating = 4.2,
            averagePrice = 18.0,
            restaurantImages = emptyList(),
            menuImage = "",
            description = "Las mejores tapas del centro histórico"
        )
    )

    // Mock callbacks - serán reemplazados por ViewModel
    val onEventClick: (Event) -> Unit = { event ->
        // TODO: Navegar a detalle del evento
        println("Clicked event: ${event.name}")
    }

    val onRestaurantClick: (Restaurant) -> Unit = { restaurant ->
        // TODO: Navegar a detalle del restaurante
        println("Clicked restaurant: ${restaurant.name}")
    }

    val onRemoveEventFavorite: (Event) -> Unit = { event ->
        // TODO: Implementar eliminación de favorito
        println("Remove event favorite: ${event.name}")
    }

    val onRemoveRestaurantFavorite: (Restaurant) -> Unit = { restaurant ->
        // TODO: Implementar eliminación de favorito
        println("Remove restaurant favorite: ${restaurant.name}")
    }
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Todos", "Eventos", "Restaurantes")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Text(
                    text = "Mis Favoritos",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        // Tab Row
        TabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        // Content
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            when (selectedTab) {
                0 -> { // Todos
                    items(events) { event ->
                        EventCard(
                            event = event,
                            onClick = { onEventClick(event) },
                            onRemoveFavorite = { onRemoveEventFavorite(event) }
                        )
                    }
                    items(restaurants) { restaurant ->
                        RestaurantCard(
                            restaurant = restaurant,
                            onClick = { onRestaurantClick(restaurant) },
                            onRemoveFavorite = { onRemoveRestaurantFavorite(restaurant) }
                        )
                    }
                }

                1 -> { // Solo Eventos
                    items(events) { event ->
                        EventCard(
                            event = event,
                            onClick = { onEventClick(event) },
                            onRemoveFavorite = { onRemoveEventFavorite(event) }
                        )
                    }
                }

                2 -> { // Solo Restaurantes
                    items(restaurants) { restaurant ->
                        RestaurantCard(
                            restaurant = restaurant,
                            onClick = { onRestaurantClick(restaurant) },
                            onRemoveFavorite = { onRemoveRestaurantFavorite(restaurant) }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventCard(
    event: Event,
    onClick: () -> Unit,
    onRemoveFavorite: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Icono de evento con fondo colorido
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Evento",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(28.dp)
                )
            }

            // Información del evento
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    // Título y categoría
                    Text(
                        text = event.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = event.category,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp
                    )
                }

                // Fecha, hora y ubicación
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${event.date} • ${event.time}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = event.location,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 11.sp
                    )
                }
            }

            // Precio y botón favorito
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                IconButton(
                    onClick = onRemoveFavorite,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Quitar de favoritos",
                        tint = Color.Red,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Text(
                    text = if (event.price > 0) "${event.price}€" else "Gratis",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantCard(
    restaurant: Restaurant,
    onClick: () -> Unit,
    onRemoveFavorite: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Imagen del restaurante o icono por defecto
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                if (restaurant.restaurantImages.isNotEmpty()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(restaurant.restaurantImages.first())
                            .crossfade(true)
                            .build(),
                        contentDescription = "Imagen del restaurante",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Restaurante",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            // Información del restaurante
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    // Nombre y tipo de comida
                    Text(
                        text = restaurant.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = restaurant.foodType,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp
                    )
                }

                // Rating y dirección
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFB300),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = restaurant.rating.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = restaurant.address,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 11.sp
                    )
                }
            }

            // Precio promedio y botón favorito
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                IconButton(
                    onClick = onRemoveFavorite,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Quitar de favoritos",
                        tint = Color.Red,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Text(
                    text = "~${restaurant.averagePrice}€",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}