package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.restaurants

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hectorgonzalez.gastrovalenciaapp.presentation.viewmodel.EventViewModel

@Composable
fun RestaurantsScreen(
    viewModel: RestaurantsViewModel = viewModel()

) {
    Button(onClick = {viewModel.fetchRestaurants()}) { }
    Text(text = "Restaurant Screen")
}