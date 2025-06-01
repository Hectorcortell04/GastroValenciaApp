package com.hectorgonzalez.gastrovalenciaapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hectorgonzalez.gastrovalenciaapp.presentation.favorites.FavoritesScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.eventDetail.EventDetailScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.events.EventsScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.login.LoginScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.privacyPolitics.PrivacyPoliticsScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.profile.ProfileScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.restaurantDetail.RestaurantDetailScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.restaurants.RestaurantsScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.splash.SplashScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.termsAndConditions.TermsAndConditionsScreen

sealed class AppScreens(val route: String) {
    data object Splash : AppScreens("splash")
    data object Login : AppScreens("login")
    data object Events : AppScreens("events")
    data object Restaurants : AppScreens("restaurants")
    data object Profile : AppScreens("profile")
    data object EventDetail : AppScreens("event")
    data object RestaurantDetail : AppScreens("restaurant")
    data object TermsAndConditions : AppScreens("termsAndConditions")
    data object PrivacyPolitics : AppScreens("privacyPolitics")
    data object Favorites : AppScreens("favorites")
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
            EventsScreen(
                navigateToEventDetail = {
                    navController.navigate(AppScreens.EventDetail.route)
                }
            )
        }

        composable(AppScreens.Restaurants.route) {
            RestaurantsScreen(
                navigateToRestaurantDetail = {
                    navController.navigate(AppScreens.RestaurantDetail.route)
                }
            )
        }

        composable(AppScreens.Profile.route) {
            ProfileScreen(
                onLogout = {
                    navController.navigate(AppScreens.Login.route) {
                        popUpTo(AppScreens.Profile.route) { inclusive = true }
                    }
                },
                navigateToTermsAndConditions = {
                    navController.navigate(AppScreens.TermsAndConditions.route)
                },
                navigateToPrivacyPolitics = {
                    navController.navigate(AppScreens.PrivacyPolitics.route)
                },
                navigateToFavorites = {
                    navController.navigate(AppScreens.Favorites.route)
                }
            )
        }

        composable(AppScreens.EventDetail.route) {
            EventDetailScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(AppScreens.RestaurantDetail.route) {
            RestaurantDetailScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(AppScreens.TermsAndConditions.route) {
            TermsAndConditionsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(AppScreens.PrivacyPolitics.route) {
            PrivacyPoliticsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(AppScreens.Favorites.route) {
            FavoritesScreen(
            )
        }
    }
}

