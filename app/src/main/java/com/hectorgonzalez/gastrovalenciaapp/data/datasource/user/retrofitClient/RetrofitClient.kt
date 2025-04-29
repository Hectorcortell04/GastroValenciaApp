package com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.retrofitClient

import com.hectorgonzalez.gastrovalenciaapp.data.datasource.event.api.EventApi
import com.hectorgonzalez.gastrovalenciaapp.data.datasource.user.api.UserApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.207:8080"

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    val api: UserApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }
}