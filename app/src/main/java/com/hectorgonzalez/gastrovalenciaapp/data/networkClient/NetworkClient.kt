package com.hectorgonzalez.gastrovalenciaapp.data.networkClient


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Gestiona la configuración de Retrofit para hacer peticiones HTTP
object NetworkClient {

    // Dirección local del backend
    private const val BASE_URL = "http://10.0.2.2:8080"

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    // Instancia de Retrofit que uso en los datasources
    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}