package com.example.elderlycare2.presentation.state

data class UserEditProfileState(
    val fullName: String = "",
    val gender: String = "",
    val phoneNumber: String = "",
    val caretaker: String = "",
    val address: String = "",
    val email: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

sealed class UserEditProfileUiEvent {
    data class UpdateFullName(val fullName: String) : UserEditProfileUiEvent()
    data class UpdateGender(val gender: String) : UserEditProfileUiEvent()
    data class UpdatePhoneNumber(val phoneNumber: String) : UserEditProfileUiEvent()
    data class UpdateCaretaker(val caretaker: String) : UserEditProfileUiEvent()
    data class UpdateAddress(val address: String) : UserEditProfileUiEvent()
    data class UpdateEmail(val email: String) : UserEditProfileUiEvent()
    object SubmitProfile : UserEditProfileUiEvent()
    object LoadProfile : UserEditProfileUiEvent()
}