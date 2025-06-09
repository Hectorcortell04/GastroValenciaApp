package com.hectorgonzalez.gastrovalenciaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.hectorgonzalez.gastrovalenciaapp.presentation.navigation.AppNavHost
import com.hectorgonzalez.gastrovalenciaapp.presentation.navigation.MainScaffold
import com.hectorgonzalez.gastrovalenciaapp.ui.theme.GastroValenciaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            GastroValenciaAppTheme {
                MainScaffold(navController = navController) {
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}
