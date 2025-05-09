package com.example.elderlycare2.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycare2.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?> = _token

    private val _role = MutableLiveData<String?>()
    val role: LiveData<String?> = _role

    init {
        // Load token and role when the ViewModel is initialized
        viewModelScope.launch(Dispatchers.IO) {
            _token.postValue(authRepository.getToken())
            _role.postValue(authRepository.getRole())
        }
    }
}