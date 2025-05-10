package com.example.elderlycare2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycare2.data.repository.SignupRepository
import com.example.elderlycare2.presentation.state.SignupEvent
import com.example.elderlycare2.presentation.state.SignupState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupRepository: SignupRepository
) : ViewModel() {

    private val _signupState = MutableStateFlow(SignupState())
    val signupState: StateFlow<SignupState> = _signupState

    fun handleEvent(event: SignupEvent) {
        when (event) {
            is SignupEvent.OnNameChange -> updateState { it.copy(name = event.name) }
            is SignupEvent.OnPassword -> updateState { it.copy(password = event.password) }
            is SignupEvent.OnEmailChange -> updateState { it.copy(email = event.email) }
            is SignupEvent.OnRoleChange -> updateState { it.copy(role = event.role) }
            is SignupEvent.OnConfirmPassword -> updateState { it.copy(confirmPassword = event.confirmPassword) }
            is SignupEvent.SignUpUser -> signUpUser(event.email, event.password, event.name)
            is SignupEvent.SignUpNurse -> signUpNurse(event.email, event.password, event.name)
            is SignupEvent.ClearSignupResults -> clearSignupResults()
            is SignupEvent.OnSubmit -> {
                // Handle OnSubmit if required
            }
        }
    }

    private fun signUpUser(email: String, password: String, name: String) {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true, error = null, isSuccess = false) }
            try {
                signupRepository.signUpUser(email, password, name)
                _signupState.value = SignupState(
                    isLoading = false,
                    isSuccess = true
                )
            } catch (e: Exception) {
                _signupState.value = SignupState(
                    isLoading = false,
                    error = e.message ?: "An unknown error occurred"
                )
            }
        }
    }

    private fun signUpNurse(email: String, password: String, name: String) {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true, error = null, isSuccess = false) }
            try {
                signupRepository.signUpNurse(email, password, name)
                _signupState.value = SignupState(
                    isLoading = false,
                    isSuccess = true
                )
            } catch (e: Exception) {
                _signupState.value = SignupState(
                    isLoading = false,
                    error = e.message ?: "An unknown error occurred"
                )
            }
        }
    }

    private fun clearSignupResults() {
        _signupState.value = SignupState()
    }

    private fun updateState(update: (SignupState) -> SignupState) {
        val current = _signupState.value ?: SignupState()
        _signupState.value = update(current)
    }
}