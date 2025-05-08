package com.example.elderlycare2.utils

// utils/AuthState.kt
sealed class AuthState {
    object Unauthenticated : AuthState()  // No user logged in
    object Loading : AuthState()         // Auth in progress
    data class Authenticated(val userId: String) : AuthState()  // Logged in (with user ID)
    data class Error(val message: String) : AuthState()         // Auth failed
}