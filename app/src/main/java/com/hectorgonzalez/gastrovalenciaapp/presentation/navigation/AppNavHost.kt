package com.hectorgonzalez.gastrovalenciaapp.navigation

import MyDiscountsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.favorites.FavoritesScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.eventDetail.EventDetailScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.events.EventsScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.login.LoginScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.privacyPolitics.PrivacyPoliticsScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.profile.ProfileScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.register.RegisterScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.restaurantDetail.RestaurantDetailScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.restaurants.RestaurantsScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.splash.SplashScreen
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.termsAndConditions.TermsAndConditionsScreen

sealed class AppScreens(val route: String) {
    data object Splash : AppScreens("splash")
    data object Login : AppScreens("login")
    data object Register : AppScreens("register")
    data object Events : AppScreens("events")
    data object Restaurants : AppScreens("restaurants")
    data object Profile : AppScreens("profile")
    data object EventDetail : AppScreens("event/{eventId}") {
        fun createRoute(eventId: Int) = "event/$eventId"
    }

    data object RestaurantDetail : AppScreens("restaurant/{restaurantId}") {
        fun createRoute(restaurantId: Int) = "restaurant/$restaurantId"
    }

    data object TermsAndConditions : AppScreens("termsAndConditions")
    data object PrivacyPolitics : AppScreens("privacyPolitics")
    data object Favorites : AppScreens("favorites")
    data object MyDiscounts : AppScreens("myDiscounts/{userId}") {
        fun createRoute(userId: Int) = "myDiscounts/$userId"
    }
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
                },
                navigateToRegisterScreen = {
                    navController.navigate(AppScreens.Register.route)
                }
            )
        }

        composable(AppScreens.Register.route) {
            RegisterScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppScreens.Events.route) {
            EventsScreen(
                navigateToEventDetail = { eventId ->
                    navController.navigate(AppScreens.EventDetail.createRoute(eventId))
                }
            )
        }

        composable(AppScreens.Restaurants.route) {
            RestaurantsScreen(
                navigateToRestaurantDetail = { restaurantId ->
                    navController.navigate(AppScreens.RestaurantDetail.createRoute(restaurantId))
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
                },
                navigateToMyDiscounts = { userId ->
                    navController.navigate(AppScreens.MyDiscounts.createRoute(userId))
                }
            )
        }

        composable(
            route = AppScreens.EventDetail.route,
            arguments = listOf(navArgument("eventId") { type = NavType.IntType })
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt("eventId") ?: 0
            EventDetailScreen(
                eventId = eventId,
                onBackClick = { navController.popBackStack() },
                navigateToMyDiscounts = { userId ->
                    navController.navigate(AppScreens.MyDiscounts.createRoute(userId))
                }
            )
        }

        composable(
            route = AppScreens.RestaurantDetail.route,
            arguments = listOf(navArgument("restaurantId") { type = NavType.IntType })
        ) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getInt("restaurantId") ?: 0
            RestaurantDetailScreen(
                restaurantId = restaurantId,
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
                onBackClick = { navController.popBackStack() },
                )
        }

        composable(
            route = AppScreens.MyDiscounts.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            MyDiscountsScreen(
                onBackClick = { navController.popBackStack() },
                userId = userId.toString()
            )
        }
    }
}