package com.example.elderlycare2.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycare2.data.api.ApiService
import com.example.elderlycare2.data.remote.request.LoginRequest
import com.example.elderlycare2.data.remote.request.SignUpRequest
import com.example.elderlycare2.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val apiService: ApiService,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<String>?>()
    val loginResult: LiveData<Result<String>?> = _loginResult

    private val _signupResult = MutableLiveData<Result<Unit>?>()
    val signupResult: LiveData<Result<Unit>?> = _signupResult

    val isLoggedIn: Boolean
        get() = sessionManager.fetchAuthToken() != null

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = try {
                val loginRequest = LoginRequest(email = email, password = password)
                val response = apiService.login(loginRequest)

                // Save token on successful login
                sessionManager.saveAuthToken(response.token)
                Result.success(response.token)
            } catch (e: Exception) {
                Result.failure(e)
            }
            result.onSuccess {
                Log.d("AuthViewModel", "Login success: Token saved.")
            }
            result.onFailure {
                Log.e("AuthViewModel", "Login failed: ${it.message}")
            }
            _loginResult.postValue(result)
        }
    }

    fun signUpUser(email: String, password: String, name: String) {
        viewModelScope.launch {
            val result = try {
                val signupRequest = SignUpRequest(
                    email = email,
                    password = password,
                    name = name
                )
                apiService.signUpUser(signupRequest)
                Result.success(Unit) // No token is generated during signup
            } catch (e: Exception) {
                Result.failure(e)
            }
            result.onSuccess {
                Log.d("AuthViewModel", "Signup success.")
            }
            result.onFailure {
                Log.e("AuthViewModel", "Signup failed: ${it.message}")
            }
            _signupResult.postValue(result)
        }
    }

    fun signUpNurse(email: String, password: String, name: String) {
        viewModelScope.launch {
            val result = try {
                val signupRequest = SignUpRequest(
                    email = email,
                    password = password,
                    name = name
                )
                apiService.signUpNurse(signupRequest)
                Result.success(Unit) // No token is generated during signup
            } catch (e: Exception) {
                Result.failure(e)
            }
            result.onSuccess {
                Log.d("AuthViewModel", "Signup success.")
            }
            result.onFailure {
                Log.e("AuthViewModel", "Signup failed: ${it.message}")
            }
            _signupResult.postValue(result)
        }
    }

    fun logout() {
        Log.d("AuthViewModel", "Logout initiated.")
        sessionManager.clearSession()
        Log.d("AuthViewModel", "Session cleared.")
    }

    fun clearAuthResults() {
        _loginResult.postValue(null)
        _signupResult.postValue(null)
    }
}