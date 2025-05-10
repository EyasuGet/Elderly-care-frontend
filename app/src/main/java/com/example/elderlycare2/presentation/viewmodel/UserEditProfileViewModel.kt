package com.example.elderlycare2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycare2.data.remote.request.UserEditProfileRequest
import com.example.elderlycare2.data.repository.UserProfileRepository
import com.example.elderlycare2.presentation.state.UserEditProfileState
import com.example.elderlycare2.presentation.state.UserEditProfileUiEvent
import com.example.elderlycare2.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserEditProfileViewModel @Inject constructor(
    private val userProfileRepository: UserProfileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserEditProfileState())
    val uiState: StateFlow<UserEditProfileState> = _uiState.asStateFlow()

    init {
        fetchUserProfile()
    }

    fun onEvent(event: UserEditProfileUiEvent) {
        when (event) {
            is UserEditProfileUiEvent.UpdateFullName -> _uiState.update { it.copy(fullName = event.fullName) }
            is UserEditProfileUiEvent.UpdateGender -> _uiState.update { it.copy(gender = event.gender) }
            is UserEditProfileUiEvent.UpdatePhoneNumber -> _uiState.update { it.copy(phoneNumber = event.phoneNumber) }
            is UserEditProfileUiEvent.UpdateCaretaker -> _uiState.update { it.copy(caretaker = event.caretaker) }
            is UserEditProfileUiEvent.UpdateAddress -> _uiState.update { it.copy(address = event.address) }
            UserEditProfileUiEvent.SubmitProfile -> submitProfile()
        }
    }

    private fun fetchUserProfile() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            userProfileRepository.getUserProfile().collect { result ->
                when (result) {
                    is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                    is NetworkResult.Success -> {
                        val profile = result.data
                        _uiState.update {
                            it.copy(
                                fullName = profile.name,
                                gender = profile.gender,
                                phoneNumber = profile.phoneNo,
                                caretaker = profile.caretaker,
                                address = profile.address,
                                email = profile.email,
                                isLoading = false
                            )
                        }
                    }
                    is NetworkResult.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
                }
            }
        }
    }

    private fun submitProfile() {
        val state = uiState.value
        val request = UserEditProfileRequest(
            name = state.fullName,
            gender = state.gender,
            phoneNo = state.phoneNumber,
            caretaker = state.caretaker,
            address = state.address,
            email = state.email
        )

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, isSuccess = false) }
            userProfileRepository.updateUserProfile(request).collect { result ->
                when (result) {
                    is NetworkResult.Success -> _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                    is NetworkResult.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
                    is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}