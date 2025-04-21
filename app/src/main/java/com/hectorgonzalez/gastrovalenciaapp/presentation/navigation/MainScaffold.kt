package com.hectorgonzalez.gastrovalenciaapp.presentation.navigation

import com.hectorgonzalez.gastrovalenciaapp.navigation.AppScreens
import com.hectorgonzalez.gastrovalenciaapp.navigation.BottomNavigationBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainScaffold(navController: NavHostController, content: @Composable () -> Unit) {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(
        AppScreens.Events.route,
        AppScreens.Restaurants.route,
        AppScreens.Profile.route
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}