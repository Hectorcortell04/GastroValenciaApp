import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .background(Color.White)
    ) {
        // Top bar with app name and search icon
        TopAppBar()

        // User profile section
        UserProfileSection()

        // Menu options
        MenuOptions(onLogOut = onLogout)

        // App version at bottom
        Spacer(modifier = Modifier.weight(1f))
        AppVersionFooter()
    }
}

@Composable
fun TopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "PERFIL",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun UserProfileSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile image
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_gallery), // Replace with actual image resource
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        // User name
        Text(
            text = "Susana Monzó",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )

        // User email
        Text(
            text = "susmonzo19@gmail.com",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
fun MenuOptions(
    onLogOut: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Configuration option
        MenuOption(
            icon = Icons.Default.Settings,
            text = "Configuración",
            showChevron = true
        )

        // Associations option
        MenuOption(
            icon = Icons.Default.LocationOn,
            text = "Asociaciones",
            showChevron = true
        )

        // My reviews option
        MenuOption(
            icon = Icons.Default.Settings,
            text = "Mis reseñas",
            showChevron = true
        )

        // My created restaurants option
        MenuOption(
            icon = Icons.Default.Add,
            text = "Mis restaurantes creados",
            showChevron = true
        )

        // Notifications option with switch
        NotificationOption()

        // Logout option
        MenuOption(
            icon = Icons.AutoMirrored.Filled.ExitToApp,
            text = "Cerrar sesión",
            showChevron = false,
            onClick = {
                FirebaseAuth.getInstance().signOut()
                onLogOut()
            }
        )
    }
}

@Composable
fun MenuOption(icon: ImageVector, text: String, showChevron: Boolean, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Gray
        )

        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
                .clickable {
                    onClick()
                },
            fontSize = 16.sp,
        )

        if (showChevron) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Navigate",
                tint = Color.Gray
            )
        }
    }

    Divider(
        modifier = Modifier.padding(start = 56.dp),
        color = Color.LightGray.copy(alpha = 0.5f)
    )
}

@Composable
fun NotificationOption() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = null,
            tint = Color.Gray
        )

        Text(
            text = "Notificaciones",
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            fontSize = 16.sp
        )

        Switch(
            checked = false,
            onCheckedChange = { /* Handle toggle */ },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = Color.LightGray
            )
        )
    }

    HorizontalDivider(
        modifier = Modifier.padding(start = 56.dp),
        color = Color.LightGray.copy(alpha = 0.5f)
    )
}

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
            Text(
                text = "LĀBERIT",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "v1.1",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen()
    }
}