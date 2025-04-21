package com.hectorgonzalez.gastrovalenciaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.hectorgonzalez.gastrovalenciaapp.navigation.AppNavHost
import com.hectorgonzalez.gastrovalenciaapp.presentation.navigation.MainScaffold
import com.hectorgonzalez.gastrovalenciaapp.presentation.screens.login.LoginScreen
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
