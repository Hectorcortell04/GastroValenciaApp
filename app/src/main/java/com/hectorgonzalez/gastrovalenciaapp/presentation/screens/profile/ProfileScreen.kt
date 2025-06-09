package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.hectorgonzalez.gastrovalenciaapp.R


// Pantalla de perfil del usuario, contiene datos personales, navegación y cierre de sesión

@Composable
fun ProfileScreen(
    onLogout: () -> Unit = {},
    navigateToTermsAndConditions: () -> Unit = {},
    navigateToPrivacyPolitics: () -> Unit = {},
    navigateToFavorites: () -> Unit = {},
    navigateToMyDiscounts: (Int) -> Unit,
    viewModel: ProfileViewModel = viewModel()
) {
    val context = LocalContext.current
    val user by viewModel.user.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var showLogoutDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        uid?.let {
            viewModel.loadUser(it, context)
        }
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            TopAppBar()
            UserProfileSection(
                userName = user?.name ?: "...",
                userImg = user?.userImage,
                userMail = user?.email ?: "..."
            )
            MenuOptions(
                onLogOut = { showLogoutDialog = true },
                navigateToTermsAndConditions = navigateToTermsAndConditions,
                navigateToPrivacyPolitics = navigateToPrivacyPolitics,
                navigateToFavorites = navigateToFavorites,
                navigateToMyDiscounts = navigateToMyDiscounts,
                userId = user?.id
                    ?: 0
            )
            Spacer(modifier = Modifier.weight(1f))
            AppVersionFooter()
        }
    }

    // Diálogo de confirmación para cerrar sesión
    if (showLogoutDialog) {
        LogoutConfirmationDialog(
            onConfirm = {
                FirebaseAuth.getInstance().signOut()
                viewModel.clearUserData(context)
                onLogout()
                showLogoutDialog = false
            },
            onDismiss = {
                showLogoutDialog = false
            }
        )
    }
}

//Muestra el diálogo de confirmación
@Composable
fun LogoutConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Cerrar Sesión",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        },
        text = {
            Text(
                text = "¿Estás seguro de que quieres cerrar sesión? Tendrás que volver a iniciar sesión para acceder a tu cuenta.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(
                    text = "Cerrar Sesión",
                    color = MaterialTheme.colorScheme.onError,
                    fontWeight = FontWeight.Medium
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = "Cancelar",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    Column {
        CenterAlignedTopAppBar(
            modifier = Modifier.height(48.dp),
            title = {
                Text(
                    text = "Perfil",
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    color = Color.Black,
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White
            )
        )

        HorizontalDivider(
            thickness = 1.dp,
            color = LightGray,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

// Sección que muestra el nombre, foto y correo del usuario
@Composable
fun UserProfileSection(
    userName: String,
    userImg: String? = null,
    userMail: String = ""
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!userImg.isNullOrEmpty()) {
            AsyncImage(
                model = userImg,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                error = painterResource(id = android.R.drawable.ic_menu_gallery),
                placeholder = painterResource(id = android.R.drawable.ic_menu_gallery)
            )
        } else {
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = userName,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = userMail,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

// Menú con todas las opciones del perfil
@Composable
fun MenuOptions(
    userId: Int,
    onLogOut: () -> Unit = {},
    navigateToTermsAndConditions: () -> Unit = {},
    navigateToPrivacyPolitics: () -> Unit = {},
    navigateToFavorites: () -> Unit,
    navigateToMyDiscounts: (Int) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        MenuOption(
            drawableId = R.drawable.ic_discount,
            text = "Mis descuentos",
            showChevron = true,
            onClick = { navigateToMyDiscounts(userId) }
        )
        MenuOption(
            drawableId = R.drawable.ic_heart,
            text = "Favoritos",
            showChevron = true,
            onClick = { navigateToFavorites() }
        )
        MenuOption(
            drawableId = R.drawable.ic_terms_and_conditions,
            text = "Términos y condiciones",
            showChevron = true,
            onClick = { navigateToTermsAndConditions() }
        )
        MenuOption(
            drawableId = R.drawable.ic_privacy_politic,
            text = "Políticas de privacidad",
            showChevron = true,
            onClick = { navigateToPrivacyPolitics() }
        )
        MenuOption(
            drawableId = R.drawable.ic_log_out,
            text = "Cerrar sesión",
            showChevron = false,
            onClick = onLogOut
        )
    }
}

// Cada fila del menú
@Composable
fun MenuOption(drawableId: Int, text: String, showChevron: Boolean, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = drawableId),
            contentDescription = null,
            tint = Color.Gray
        )
        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            fontSize = 16.sp,
        )

        if (showChevron) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_richt),
                contentDescription = "Navigate",
                tint = Color.Gray
            )
        }
    }

    HorizontalDivider(
        modifier = Modifier.padding(start = 56.dp),
        color = Color.LightGray.copy(alpha = 0.5f)
    )
}

// Muestra el logo y la versión de la app
@Composable
fun AppVersionFooter() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_logo_gastrovalencia),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "v1.1",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}