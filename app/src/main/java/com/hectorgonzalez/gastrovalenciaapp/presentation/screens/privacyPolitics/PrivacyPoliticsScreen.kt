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

/**
 * Función que contiene la política de privacidad por defecto
 * Puedes modificar este contenido según las necesidades específicas de tu aplicación
 */
private fun getDefaultPrivacyPolitics(): String {
    return """
        **Última actualización: [26 de mayo 2025]**
        
        **POLÍTICA DE PRIVACIDAD DE GASTROVALENCIA**
        
        En GastroValencia, nos comprometemos a proteger su privacidad y garantizar el uso responsable de su información personal. Esta Política de Privacidad explica cómo recopilamos, utilizamos, divulgamos y protegemos su información cuando utiliza nuestra aplicación móvil.
        
        **1. INFORMACIÓN QUE RECOPILAMOS**
        
        **Información Personal:**
        • Nombre y apellidos
        • Dirección de correo electrónico
        • Número de teléfono (opcional)
        • Fecha de nacimiento (opcional)
        • Preferencias gastronómicas
        
        **Información de Uso:**
        • Restaurantes visitados y marcados como favoritos
        • Reseñas y calificaciones realizadas
        • Búsquedas y filtros utilizados
        • Tiempo de uso de la aplicación
        • Funciones más utilizadas
        
        **Información de Ubicación:**
        • Ubicación aproximada para mostrar restaurantes cercanos
        • Historial de ubicaciones visitadas (solo si otorga permisos)
        
        **Información del Dispositivo:**
        • Tipo de dispositivo y sistema operativo
        • Identificadores únicos del dispositivo
        • Información de red y conectividad
        
        **2. CÓMO UTILIZAMOS SU INFORMACIÓN**
        
        Utilizamos su información personal para:
        
        • Proporcionar y mejorar nuestros servicios
        • Personalizar su experiencia en la aplicación
        • Mostrar restaurantes relevantes según su ubicación
        • Enviar notificaciones sobre ofertas y promociones
        • Responder a sus consultas y proporcionar soporte técnico
        • Analizar el uso de la aplicación para mejoras futuras
        • Cumplir con obligaciones legales
        
        **3. COMPARTIR INFORMACIÓN**
        
        **No vendemos** su información personal a terceros. Podemos compartir su información en las siguientes circunstancias:
        
        • **Restaurantes Asociados:** Información básica para procesar reservas
        • **Proveedores de Servicios:** Empresas que nos ayudan a operar la aplicación
        • **Requisitos Legales:** Cuando sea requerido por ley o autoridades competentes
        • **Reseñas Públicas:** Las reseñas que publique serán visibles para otros usuarios
        
        **4. COOKIES Y TECNOLOGÍAS SIMILARES**
        
        Utilizamos cookies y tecnologías similares para:
        
        • Recordar sus preferencias y configuraciones
        • Analizar el rendimiento de la aplicación
        • Personalizar contenido y anuncios
        • Proporcionar funciones de redes sociales
        
        Puede gestionar las preferencias de cookies desde la configuración de la aplicación.
        
        **5. SEGURIDAD DE DATOS**
        
        Implementamos medidas de seguridad técnicas y organizativas para proteger su información:
        
        • Encriptación de datos en tránsito y en reposo
        • Acceso restringido a información personal
        • Monitoreo regular de sistemas de seguridad
        • Auditorías de seguridad periódicas
        
        **6. SUS DERECHOS**
        
        De acuerdo con el RGPD y normativas locales, usted tiene derecho a:
        
        • **Acceso:** Solicitar una copia de su información personal
        • **Rectificación:** Corregir información inexacta o incompleta
        • **Eliminación:** Solicitar la eliminación de su información
        • **Portabilidad:** Recibir sus datos en formato estructurado
        • **Oposición:** Oponerse al procesamiento de sus datos
        • **Limitación:** Restringir el procesamiento de sus datos
        
        **7. RETENCIÓN DE DATOS**
        
        Conservamos su información personal solo durante el tiempo necesario para:
        
        • Proporcionar nuestros servicios
        • Cumplir con obligaciones legales
        • Resolver disputas
        • Hacer cumplir nuestros acuerdos
        
        **8. MENORES DE EDAD**
        
        Nuestra aplicación no está dirigida a menores de 16 años. No recopilamos intencionalmente información personal de menores sin el consentimiento parental apropiado.
        
        **9. CAMBIOS EN ESTA POLÍTICA**
        
        Podemos actualizar esta Política de Privacidad ocasionalmente. Le notificaremos sobre cambios significativos a través de la aplicación o por correo electrónico.
        
        **10. TRANSFERENCIAS INTERNACIONALES**
        
        Su información puede ser transferida y procesada en países fuera del Espacio Económico Europeo. Garantizamos protecciones adecuadas mediante cláusulas contractuales estándar.
        
        **11. CONTACTO**
        
        Para ejercer sus derechos o hacer consultas sobre privacidad:
        
        **Responsable de Protección de Datos:**
        Email: privacidad@gastrovalencia.com
        Teléfono: +34 XXX XXX XXX
        Dirección: [Dirección física de la empresa]
        
        **Autoridad de Control:**
        Agencia Española de Protección de Datos (AEPD)
        Web: www.aepd.es
        
        **© 2024 GastroValencia. Todos los derechos reservados.**
    """.trimIndent()
}

@Preview(showBackground = true)
@Composable
fun PrivacyPoliticsScreenPreview() {
    MaterialTheme {
        PrivacyPoliticsScreen(
            onBackClick = { /* Preview action */ }
        )
    }
}