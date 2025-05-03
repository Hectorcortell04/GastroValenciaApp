package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(
    onLogout: () -> Unit = {}
) {
    val user = FirebaseAuth.getInstance().currentUser
    val email = user?.email ?: "Usuario desconocido"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Perfil", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Email: $email")

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = {
            FirebaseAuth.getInstance().signOut()
            onLogout()
        }) {
            Text(text = "Cerrar sesión")
        }
    }
}