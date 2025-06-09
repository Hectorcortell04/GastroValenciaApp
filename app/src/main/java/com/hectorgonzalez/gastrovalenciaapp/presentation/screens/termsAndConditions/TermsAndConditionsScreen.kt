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
        Última actualización: [26 de mayo de 2025]

TÉRMINOS Y CONDICIONES DE USO DE GASTROVALENCIA

Al utilizar la aplicación móvil GastroValencia, usted acepta las condiciones establecidas a continuación. Le recomendamos leer atentamente este documento antes de continuar utilizando la aplicación.

1. ACEPTACIÓN DE LOS TÉRMINOS

Al acceder y utilizar la aplicación GastroValencia, usted acepta estar legalmente vinculado por estos Términos y Condiciones. Si no está de acuerdo con alguno de estos términos, le rogamos que no utilice la aplicación.

2. DESCRIPCIÓN DEL SERVICIO

GastroValencia es una aplicación móvil centrada en ofrecer información gastronómica sobre restaurantes, bares y eventos culinarios en la ciudad de Valencia (España). A través de la app, los usuarios pueden acceder a reseñas, valoraciones, cartas, fotografías, localizaciones y promociones especiales asociadas a establecimientos registrados.

3. USO ACEPTABLE

Al utilizar la aplicación, usted se compromete a:

• Usarla únicamente con fines legales y personales
• No interferir, dañar o alterar el funcionamiento técnico de la aplicación
• No acceder o intentar acceder a cuentas, bases de datos o datos que no estén autorizados para usted
• Respetar los derechos de propiedad intelectual de GastroValencia y de terceros

4. PRIVACIDAD Y DATOS

La información personal que nos proporcione será tratada de acuerdo con nuestra Política de Privacidad. Al utilizar la aplicación, usted acepta que recopilemos, procesemos y almacenemos sus datos conforme a dicha política, con el fin de mejorar la experiencia de usuario y personalizar el contenido mostrado.

5. CONTENIDO GENERADO POR USUARIOS

Los usuarios pueden enviar reseñas, comentarios y calificaciones de establecimientos. En este sentido:

• Usted es el único responsable del contenido que publique
• Nos reservamos el derecho de moderar, ocultar o eliminar contenido que infrinja estos términos
• No está permitido publicar contenido ofensivo, discriminatorio, difamatorio o que constituya spam

6. LIMITACIÓN DE RESPONSABILIDAD

La información mostrada en la app proviene de fuentes propias y de terceros (restaurantes, eventos, colaboradores, etc.). Aunque hacemos esfuerzos razonables para mantenerla actualizada y precisa, no garantizamos su exactitud ni completitud. El uso de la aplicación es bajo su propia responsabilidad.

7. MODIFICACIONES

GastroValencia se reserva el derecho de modificar estos Términos y Condiciones en cualquier momento. Los cambios entrarán en vigor una vez publicados en la aplicación. Se recomienda revisar periódicamente esta sección.

8. TERMINACIÓN

Podemos suspender o finalizar su acceso a la aplicación de manera temporal o permanente, sin previo aviso, en caso de detectar uso indebido, violación de estos términos o comportamiento perjudicial hacia la plataforma o sus usuarios.

9. LEY APLICABLE

Este acuerdo se rige por la legislación vigente en España. Cualquier disputa relacionada con estos términos será resuelta ante los tribunales competentes de la ciudad de Valencia.

10. CONTACTO

Para cualquier consulta relacionada con estos términos y condiciones, puede contactarnos a través de:

Email: soporte@gastrovalencia.com
Teléfono: +34 960 456 789

© 2025 GastroValencia. Todos los derechos reservados.
    """.trimIndent()
}