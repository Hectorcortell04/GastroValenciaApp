package com.hectorgonzalez.gastrovalenciaapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.events.EventsScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.login.LoginScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.profile.ProfileScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.restaurants.RestaurantsScreen

sealed class AppScreens(val route: String) {
    data object Login : AppScreens("login")
    data object Events : AppScreens("events")
    data object Restaurants : AppScreens("restaurants")
    data object Profile : AppScreens("profile")
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.Login.route
    ) {
        composable(AppScreens.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(AppScreens.Events.route) {
                        popUpTo(AppScreens.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(AppScreens.Events.route) {
            EventsScreen()
        }
        composable(AppScreens.Restaurants.route) {
            RestaurantsScreen()
        }
        composable(AppScreens.Profile.route) {
            ProfileScreen()
        }
    }
}