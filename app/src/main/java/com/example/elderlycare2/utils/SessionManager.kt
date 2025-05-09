package com.example.elderlycare2.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SessionManager @Inject constructor(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    // Save auth token
    fun saveAuthToken(token: String) {
        preferences.edit().putString("auth_token", token).apply()
    }

    // Retrieve auth token
    fun getAuthToken(): String? {
        return preferences.getString("auth_token", null)
    }

    // Save user role
    fun saveUserRole(role: String) {
        preferences.edit().putString("user_role", role).apply()
    }

    // Retrieve user role
    fun getUserRole(): String? {
        return preferences.getString("user_role", null)
    }

    // Clear session
    fun clearSession() {
        preferences.edit().clear().apply()
    }
}