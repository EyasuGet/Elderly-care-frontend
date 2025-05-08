package com.example.elderlycare2.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycare2.data.local.SessionManager
import com.example.elderlycare2.data.remote.response.AuthResponse
import com.example.elderlycare2.data.repository.AuthRepository
import com.example.elderlycare2.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



// presentation/viewmodel/AuthViewModel.kt
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository,
    val sessionManager: SessionManager
) : ViewModel() {
//    val authState by AuthViewModel.authState.collectAsState()
    private val _loginState = MutableStateFlow<ApiResult<AuthResponse>>(ApiResult.Idle)
    val loginState: StateFlow<ApiResult<AuthResponse>> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = ApiResult.Loading
            _loginState.value = repo.loginUser(email, password)
        }
    }
}