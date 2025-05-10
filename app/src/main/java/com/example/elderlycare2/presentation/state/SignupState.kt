package com.example.elderlycare2.presentation.state

data class SignupState(
    val isLoading: Boolean = false,
    val isSignedUp: Boolean = false,
    val error: String? = null,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val onSubmit: Boolean = false,
    val isSuccess: Boolean = false,
    val role: String = ""
)

sealed class SignupEvent {
    data class OnNameChange(val name: String) : SignupEvent()
    data class OnEmailChange(val email: String) : SignupEvent()
    data class OnPassword(val password: String) : SignupEvent()
    data class OnConfirmPassword(val confirmPassword: String) : SignupEvent()
    data class SignUpUser(val email: String, val password: String, val name: String) : SignupEvent()
    data class SignUpNurse(val email: String, val password: String, val name: String) : SignupEvent()
    data class OnRoleChange(val role: String) : SignupEvent()
    object OnSubmit : SignupEvent()
    object ClearSignupResults : SignupEvent()
}