package com.example.elderlycare2.data.storage

import android.content.Context
import android.content.SharedPreferences

class LocalStorage(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("elderlycare_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TOKEN = "auth_token"
        private const val KEY_ROLE = "user_role"
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    fun saveRole(role: String) {
        sharedPreferences.edit().putString(KEY_ROLE, role).apply()
    }

    fun getRole(): String? {
        return sharedPreferences.getString(KEY_ROLE, null)
    }

    fun clearSession() {
        sharedPreferences.edit().clear().apply()
    }
}