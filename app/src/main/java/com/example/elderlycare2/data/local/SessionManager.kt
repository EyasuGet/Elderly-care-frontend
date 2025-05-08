package com.example.elderlycare2.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val prefs = context.getSharedPreferences("ElderlyCarePrefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TOKEN = "JWT_TOKEN"
        private const val KEY_ROLE = "USER_ROLE"
    }

    fun saveToken(token: String) {
        prefs.edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken(): String? = prefs.getString(KEY_TOKEN, null)

    fun clearToken() {
        prefs.edit().remove(KEY_TOKEN).apply()
    }

    fun saveRole(role: String) {
        prefs.edit().putString(KEY_ROLE, role).apply()
    }

    fun getRole(): String? = prefs.getString(KEY_ROLE, null)

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
