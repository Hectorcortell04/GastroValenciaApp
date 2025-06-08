package com.hectorgonzalez.gastrovalenciaapp.presentation.screens.privacyPolitics

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPoliticsScreen(
    onBackClick: () -> Unit,
    privacyPolitics: String = getDefaultPrivacyPolitics()
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Política de Privacidad",
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
            // Contenido de política de privacidad
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = privacyPolitics,
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


private fun getDefaultPrivacyPolitics(): String {
    return """
       1. INFORMACIÓN QUE RECOPILAMOS
Información personal:
Nombre y apellidos

Correo electrónico

Número de teléfono

Fecha de nacimiento

Preferencias gastronómicas

Información de uso:
Restaurantes marcados como favoritos

Reseñas y puntuaciones realizadas

Filtros y búsquedas utilizadas

Tiempo de navegación y uso de funcionalidades

Interacciones dentro de la app

Información de ubicación:
Ubicación aproximada para mostrar contenido relevante

Historial de localizaciones (solo si se concede permiso explícito)

Información del dispositivo:
Tipo de dispositivo (por ejemplo, Android o iPhone)

Versión del sistema operativo

Identificadores únicos (ID de dispositivo)

Datos de conexión (Wi-Fi, red móvil, etc.)

2. CÓMO UTILIZAMOS SU INFORMACIÓN
La información recopilada se utiliza para:

Ofrecer funcionalidades clave de la aplicación

Personalizar su experiencia de usuario

Mostrarle contenido y recomendaciones adaptadas a su ubicación y preferencias

Enviarle notificaciones sobre eventos, descuentos o nuevos restaurantes

Atender consultas y brindar soporte técnico

Realizar análisis anónimos para mejorar la calidad de nuestros servicios

Cumplir con obligaciones legales y de seguridad

3. COMPARTICIÓN DE DATOS
No vendemos sus datos personales. Solo compartimos su información en los siguientes casos:

Restaurantes colaboradores: para gestionar ofertas o reservas que usted solicite

Proveedores tecnológicos: servicios de almacenamiento en la nube, análisis y comunicación

Obligaciones legales: si lo exige una autoridad competente

Contenido público: las reseñas y calificaciones que realice serán visibles para otros usuarios

4. COOKIES Y TECNOLOGÍAS SIMILARES
Utilizamos cookies y tecnologías de seguimiento para:

Recordar sus preferencias

Medir el rendimiento de la aplicación

Mejorar la experiencia de usuario

Mostrar contenido relevante

Puede configurar o desactivar estas funciones desde los ajustes de la aplicación.

5. SEGURIDAD DE LOS DATOS
Aplicamos medidas técnicas y organizativas para proteger su información, incluyendo:

Encriptación de datos sensibles

Accesos limitados y autenticados a la base de datos

Monitorización constante de vulnerabilidades

Auditorías internas periódicas de seguridad

6. DERECHOS DEL USUARIO
En cumplimiento del RGPD, usted puede ejercer en cualquier momento los siguientes derechos:

Acceso a sus datos personales

Rectificación de datos incorrectos

Supresión de su información cuando lo desee

Limitación del tratamiento en determinados casos

Oposición al uso de sus datos para fines específicos

Portabilidad de sus datos en formato estructurado

7. RETENCIÓN DE INFORMACIÓN
Solo conservamos sus datos mientras sean necesarios para:

Prestar nuestros servicios

Cumplir con la legislación vigente

Resolver disputas o problemas técnicos

Hacer valer nuestras condiciones de uso

8. MENORES DE EDAD
La aplicación no está diseñada para menores de 16 años. Si descubrimos que hemos recopilado datos sin consentimiento parental, procederemos a eliminarlos de forma inmediata.

9. CAMBIOS EN LA POLÍTICA
Podemos actualizar esta política en función de cambios legales o mejoras del servicio. Le notificaremos de cualquier modificación relevante a través de la aplicación o por correo electrónico.

10. TRANSFERENCIAS INTERNACIONALES
Podemos almacenar y procesar su información en servidores ubicados fuera del Espacio Económico Europeo (por ejemplo, servicios en la nube como Firebase). Garantizamos un nivel adecuado de protección mediante cláusulas contractuales tipo aprobadas por la Comisión Europea.

11. CONTACTO
Si desea ejercer sus derechos o tiene alguna pregunta relacionada con la privacidad, puede contactar con nuestro delegado de protección de datos:

Responsable de Protección de Datos
Email: privacidad@gastrovalencia.com
Teléfono: +34 960 123 456
Dirección: Carrer de les Barques, 12 – 4º, 46002 València, España

Autoridad Supervisora:
Agencia Española de Protección de Datos (AEPD)
www.aepd.es


    """.trimIndent()
}

@Preview(showBackground = true)
@Composable
fun PrivacyPoliticsScreenPreview() {
    MaterialTheme {
        PrivacyPoliticsScreen(
            onBackClick = {}
        )
    }
}