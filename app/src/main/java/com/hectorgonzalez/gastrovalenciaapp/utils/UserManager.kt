package com.hectorgonzalez.gastrovalenciaapp.utils

import android.content.Context

/**
 * Utilidad para gestionar los datos del usuario almacenados localmente
 */
object UserManager {

    private const val USER_PREFERENCES = "user_preferences"
    private const val USER_ID_KEY = "user_id"

    /**
     * Guarda el ID del usuario en SharedPreferences
     */
    fun saveUserId(context: Context, userId: Int) {
        val sharedPrefs = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
        sharedPrefs.edit()
            .putInt(USER_ID_KEY, userId)
            .apply()
    }

    /**
     * Obtiene el ID del usuario desde SharedPreferences
     * @return El ID del usuario o null si no existe
     */
    fun getUserId(context: Context): Int? {
        val sharedPrefs = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
        val userId = sharedPrefs.getInt(USER_ID_KEY, -1)
        return if (userId != -1) userId else null
    }

    /**
     * Limpia todos los datos del usuario almacenados localmente
     */
    fun clearUserData(context: Context) {
        val sharedPrefs = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
        sharedPrefs.edit()
            .remove(USER_ID_KEY)
            .apply()
    }

    /**
     * Verifica si hay un usuario logueado (si existe un ID guardado)
     */
    fun isUserLoggedIn(context: Context): Boolean {
        return getUserId(context) != null
    }
}