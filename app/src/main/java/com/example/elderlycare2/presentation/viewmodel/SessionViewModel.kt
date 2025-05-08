package com.example.elderlycare2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.elderlycare2.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    val token: String? = authRepository.getToken()
    val role: String? = authRepository.getRole()
}
