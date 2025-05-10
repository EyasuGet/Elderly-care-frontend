package com.example.elderlycare2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycare2.data.api.UserProfileApi
import com.example.elderlycare2.data.remote.request.UserEditProfileRequest
import com.example.elderlycare2.presentation.state.UserEditProfileState
import com.example.elderlycare2.presentation.state.UserEditProfileUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val api: UserProfileApi
) : ViewModel() {

    private val _state = MutableStateFlow(UserEditProfileState())
    val state: StateFlow<UserEditProfileState> = _state

    fun onEvent(event: UserEditProfileUiEvent) {
        when (event) {
            is UserEditProfileUiEvent.UpdateFullName -> _state.value = _state.value.copy(fullName = event.fullName)
            is UserEditProfileUiEvent.UpdateGender -> _state.value = _state.value.copy(gender = event.gender)
            is UserEditProfileUiEvent.UpdatePhoneNumber -> _state.value = _state.value.copy(phoneNumber = event.phoneNumber)
            is UserEditProfileUiEvent.UpdateCaretaker -> _state.value = _state.value.copy(caretaker = event.caretaker)
            is UserEditProfileUiEvent.UpdateAddress -> _state.value = _state.value.copy(address = event.address)
            is UserEditProfileUiEvent.UpdateEmail -> _state.value = _state.value.copy(email = event.email)
            is UserEditProfileUiEvent.LoadProfile -> loadProfile()
            is UserEditProfileUiEvent.SubmitProfile -> submitProfile()
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            val response = api.getUserProfile()
            if (response.isSuccessful && response.body() != null) {
                val profile = response.body()!!
                _state.value = _state.value.copy(
                    fullName = profile.name ?: "",
                    gender = profile.gender ?: "",
                    phoneNumber = profile.phoneNo ?: "",
                    caretaker = profile.caretaker ?: "",
                    address = profile.address ?: "",
                    email = profile.email ?: "",
                    isLoading = false,
                    error = null
                )
            } else {
                _state.value = _state.value.copy(isLoading = false, error = "Failed to load profile")
            }
        }
    }

    private fun submitProfile() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null, isSuccess = false)
            val request = UserEditProfileRequest(
                name = _state.value.fullName,
                email = _state.value.email,
                caretaker = _state.value.caretaker,
                gender = _state.value.gender,
                phoneNo = _state.value.phoneNumber,
                address = _state.value.address
            )
            val response = api.updateUserProfile(request)
            if (response.isSuccessful) {
                _state.value = _state.value.copy(isLoading = false, isSuccess = true)
            } else {
                _state.value = _state.value.copy(isLoading = false, error = "Failed to update profile")
            }
        }
    }
}