package com.example.elderlycare2.presentation.state

data class AuthState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)

sealed class AuthEvent {
    data class Login(val email: String, val password: String) : AuthEvent()
    data class SignUpUser(val email: String, val password: String, val name: String) : AuthEvent()
    data class SignUpNurse(val email: String, val password: String, val name: String) : AuthEvent()
    object Logout : AuthEvent()
    object ClearAuthResults : AuthEvent()
}