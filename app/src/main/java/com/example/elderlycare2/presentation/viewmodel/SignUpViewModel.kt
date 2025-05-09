package com.example.elderlycare2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycare2.data.remote.request.SignUpRequest
import com.example.elderlycare2.data.remote.response.AuthResponse
import com.example.elderlycare2.data.repository.AuthRepository
import com.example.elderlycare2.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _signUpState = MutableStateFlow<ApiResult<AuthResponse>>(ApiResult.Loading)
    val signUpState: StateFlow<ApiResult<AuthResponse>> = _signUpState

    fun signUp(name: String, email: String, password: String) {
        viewModelScope.launch {
            _signUpState.value = ApiResult.Loading
            val result = authRepository.signUpUser(SignUpRequest(name, email, password))
            _signUpState.value = result
        }
    }
}