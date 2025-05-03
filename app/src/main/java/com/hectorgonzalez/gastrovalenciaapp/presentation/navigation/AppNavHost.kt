package com.hectorgonzalez.gastrovalenciaapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.events.EventsScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.login.LoginScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.profile.ProfileScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.restaurants.RestaurantsScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.splash.SplashScreen

sealed class AppScreens(val route: String) {
    data object Splash : AppScreens("splash")
    data object Login : AppScreens("login")
    data object Events : AppScreens("events")
    data object Restaurants : AppScreens("restaurants")
    data object Profile : AppScreens("profile")
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.Splash.route
    ) {
        composable(AppScreens.Splash.route) {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(AppScreens.Login.route) {
                        popUpTo(AppScreens.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(AppScreens.Events.route) {
                        popUpTo(AppScreens.Splash.route) { inclusive = true }
                    }
                }
            )
        }

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
            ProfileScreen(
                onLogout = {
                    navController.navigate(AppScreens.Login.route) {
                        popUpTo(AppScreens.Profile.route) { inclusive = true }
                    }
                }
            )
        }

    }
}

