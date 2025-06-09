package com.hectorgonzalez.gastrovalenciaapp.utils

import android.content.Context

// Objeto para guardar y recuperar el ID del usuario
object UserManager {

    private const val USER_PREFERENCES = "user_preferences"
    private const val USER_ID_KEY = "user_id"

    // Guarda el ID del usuario en SharedPreferences
    fun saveUserId(context: Context, userId: Int) {
        val sharedPrefs = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
        sharedPrefs.edit()
            .putInt(USER_ID_KEY, userId)
            .apply()
    }

    // Recupera el ID del usuario desde SharedPreferences
    fun getUserId(context: Context): Int? {
        val sharedPrefs = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
        val userId = sharedPrefs.getInt(USER_ID_KEY, -1)
        return if (userId != -1) userId else null
    }
}