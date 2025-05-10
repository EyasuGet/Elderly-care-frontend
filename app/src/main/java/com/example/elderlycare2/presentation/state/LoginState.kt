package com.example.elderlycare2.presentation.state

data class LoginState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val email: String = "",
    val password: String = "",
    val onSubmit: Boolean = false,
    val isSuccess: Boolean = false,
    val role: String = ""
)

sealed class LoginEvent {
    data class OnEmailChange(val email: String) : LoginEvent()
    data class OnPasswordChange(val password: String) : LoginEvent()
    data class Login(val email: String, val password: String) : LoginEvent()
    object OnSubmit : LoginEvent()
    object ClearLoginResults : LoginEvent()
}