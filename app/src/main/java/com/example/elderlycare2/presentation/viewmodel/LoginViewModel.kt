package com.example.elderlycare2.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycare2.data.remote.response.LoginResponse
import com.example.elderlycare2.data.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginState = MutableLiveData<LoginResponse>()
    val loginState: LiveData<LoginResponse> = _loginState

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loginUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = loginRepository.loginUser(email, password)
                _loginState.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }
}