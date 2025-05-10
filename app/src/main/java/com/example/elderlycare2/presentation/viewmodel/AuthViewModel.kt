package com.example.elderlycare2.presentation.viewmodel

//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.elderlycare2.data.api.ApiService
//import com.example.elderlycare2.data.model.Role
//import com.example.elderlycare2.data.remote.request.LoginRequest
//import com.example.elderlycare2.data.remote.request.SignUpRequest
//import com.example.elderlycare2.utils.NetworkResult
//import com.example.elderlycare2.utils.SessionManager
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class AuthViewModel @Inject constructor(
//    private val apiService: ApiService,
//    private val sessionManager: SessionManager
//) : ViewModel() {
//
//    private val _loginResult = MutableLiveData<Result<String>?>()
//    val loginResult: LiveData<Result<String>?> = _loginResult
//
//    private val _signupResult = MutableLiveData<NetworkResult<Unit>?>()
//    val signupResult: LiveData<NetworkResult<Unit>?> = _signupResult
//
//    val isLoggedIn: Boolean
//        get() = sessionManager.getAuthToken() != null
//
//    fun login(email: String, password: String) {
//        viewModelScope.launch {
//            val result = try {
//                val loginRequest = LoginRequest(email = email, password = password)
//                val response = apiService.login(loginRequest)
//
//                // Save token on successful login
//                sessionManager.saveAuthToken(response.token)
//                Result.success(response.token)
//            } catch (e: Exception) {
//                Result.failure(e)
//            }
//            result.onSuccess {
//                Log.d("AuthViewModel", "Login success: Token saved.")
//            }
//            result.onFailure {
//                Log.e("AuthViewModel", "Login failed: ${it.message}")
//            }
//            _loginResult.postValue(result)
//        }
//    }
//
//    fun signUp(email: String, password: String, name: String, role: Role) {
//        viewModelScope.launch {
//            // Emit loading state
//            _signupResult.postValue(NetworkResult.Loading())
//
//            // Try to make the API call
//            val result = try {
//                val signupRequest = SignUpRequest(
//                    email = email,
//                    password = password,
//                    name = name,
//                )
//
//                // Call the appropriate endpoint based on the role
//                when (role) {
//                    Role.USER -> apiService.signUpUser(signupRequest)
//                    Role.NURSE -> apiService.signUpNurse(signupRequest)
//                }
//
//                // Emit success state
//                NetworkResult.Success(Unit)
//            } catch (e: Exception) {
//                // Emit error state
//                NetworkResult.Error<Unit>(e.message ?: "An unknown error occurred")
//            }
//
//            // Post the result to LiveData
//            _signupResult.postValue(result)
//        }
//    }
//
//    fun logout() {
//        Log.d("AuthViewModel", "Logout initiated.")
//        sessionManager.clearSession()
//        Log.d("AuthViewModel", "Session cleared.")
//    }
//
//    fun clearAuthResults() {
//        _loginResult.postValue(null)
//        _signupResult.postValue(null)
//    }
//}