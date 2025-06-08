package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.restaurants

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RestaurantsScreen(
    navigateToRestaurantDetail: (Int) -> Unit,
    viewModel: RestaurantsViewModel = viewModel()

) {
    var searchText by remember { mutableStateOf("") }
    val restaurants = viewModel.restaurants
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchRestaurants(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange ={
                searchText = it
                viewModel.searchRestaurantsByName(it)
            },
            placeholder = { Text("Buscar") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Text("Cargando restaurantes...")
        } else if (error != null) {
            Text("Error: $error")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(restaurants.size) { index ->
                    val restaurant = restaurants[index]
                    RestaurantCard(
                        restaurant = restaurant,
                        onLikeClick = { viewModel.toggleRestaurantLike(restaurant.id, context)}, //TODO hacer like
                        onClick = { navigateToRestaurantDetail.invoke(restaurant.id) },
                    )
                }
            }
        }
    }
}