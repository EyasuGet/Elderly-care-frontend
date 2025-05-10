package com.example.elderlycare2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycare2.data.repository.LoginRepository
import com.example.elderlycare2.presentation.state.LoginEvent
import com.example.elderlycare2.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    fun handleEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChange -> updateState { it.copy(email = event.email) }
            is LoginEvent.OnPasswordChange -> updateState { it.copy(password = event.password) }
            is LoginEvent.Login -> login(event.email, event.password)
            is LoginEvent.ClearLoginResults -> clearLoginResults()

            else ->{}
        }

    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true, error = null, isSuccess = false) }
            try {
                val response = loginRepository.login(email, password)
                _loginState.value = LoginState(
                    isLoading = false,
                    isSuccess = response.token != null,
                    role = response.role ?: "",
                    email = email,
                    password = password,
                )
            } catch (e: Exception) {
                _loginState.value = LoginState(
                    isLoading = false,
                    isSuccess = false,
                    error = e.message ?: "An unknown error occurred"
                )
            }
        }
    }

    private fun clearLoginResults() {
        _loginState.value = LoginState()
    }

    private fun updateState(update: (LoginState) -> LoginState) {
        val current = _loginState.value
        _loginState.value = update(current)
    }
}