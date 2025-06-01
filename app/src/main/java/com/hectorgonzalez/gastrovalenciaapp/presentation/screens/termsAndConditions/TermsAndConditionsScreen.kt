package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.termsAndConditions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndConditionsScreen(
    onBackClick: () -> Unit,
    termsAndConditions: String = getDefaultTermsAndConditions()
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Términos y Condiciones",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp
            ) {
                Button(
                    onClick = onBackClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 8.dp
                    )
                ) {
                    Text(
                        text = "Entendido",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            // Contenido de términos y condiciones
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = termsAndConditions,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        lineHeight = 24.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

/**
 * Función que contiene los términos y condiciones por defecto
 * Puedes modificar este contenido según tus necesidades
 */
private fun getDefaultTermsAndConditions(): String {
    return """
        **Última actualización: [26 de mayo 2025]**
        
        **1. ACEPTACIÓN DE LOS TÉRMINOS**
        
        Al utilizar la aplicación GastroValencia, usted acepta estar sujeto a estos términos y condiciones de uso. Si no está de acuerdo con alguno de estos términos, no utilice esta aplicación.
        
        **2. DESCRIPCIÓN DEL SERVICIO**
        
        GastroValencia es una aplicación móvil que proporciona información sobre restaurantes, bares y establecimientos gastronómicos en Valencia, España. Nuestro servicio incluye reseñas, ubicaciones, menús y recomendaciones.
        
        **3. USO ACEPTABLE**
        
        • Utilizar la aplicación únicamente para fines legales y apropiados
        • No interferir con el funcionamiento de la aplicación
        • No intentar acceder a información no autorizada
        • Respetar los derechos de propiedad intelectual
        
        **4. PRIVACIDAD Y DATOS**
        
        Su privacidad es importante para nosotros. Recopilamos y utilizamos su información personal de acuerdo con nuestra Política de Privacidad. Al usar esta aplicación, usted consiente la recopilación y uso de información según se describe en dicha política.
        
        **5. CONTENIDO GENERADO POR USUARIOS**
        
        • Las reseñas y comentarios son responsabilidad de quien los publica
        • Nos reservamos el derecho de moderar y eliminar contenido inapropiado
        • No se permite contenido ofensivo, difamatorio o spam
        
        **6. LIMITACIÓN DE RESPONSABILIDAD**
        
        La información proporcionada en la aplicación es solo para fines informativos. No garantizamos la exactitud, completitud o actualidad de la información. El uso de la aplicación es bajo su propio riesgo.
        
        **7. MODIFICACIONES**
        
        Nos reservamos el derecho de modificar estos términos y condiciones en cualquier momento. Las modificaciones serán efectivas inmediatamente después de su publicación en la aplicación.
        
        **8. TERMINACIÓN**
        
        Podemos suspender o terminar su acceso a la aplicación en cualquier momento, sin previo aviso, por cualquier motivo, incluyendo el incumplimiento de estos términos.
        
        **9. LEY APLICABLE**
        
        Estos términos se regirán e interpretarán de acuerdo con las leyes de España. Cualquier disputa será sometida a la jurisdicción exclusiva de los tribunales españoles.
        
        **10. CONTACTO**
        
        Si tiene preguntas sobre estos términos y condiciones, puede contactarnos a través de:
        
        Email: soporte@gastrovalencia.com
        Teléfono: +34 XXX XXX XXX
        
        **© 2024 GastroValencia. Todos los derechos reservados.**
    """.trimIndent()
}

@Preview(showBackground = true)
@Composable
fun TermsAndConditionsScreenPreview() {
    MaterialTheme {
        TermsAndConditionsScreen(
            onBackClick = { /* Preview action */ }
        )
    }
}