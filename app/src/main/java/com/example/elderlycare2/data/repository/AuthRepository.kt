package com.example.elderlycare2.data.repository

import com.example.elderlycare2.data.local.SessionManager
import com.example.elderlycare2.data.remote.api.AuthApi
import com.example.elderlycare2.data.remote.api.request.LoginRequest
import com.example.elderlycare2.data.remote.api.request.SignUpRequest
import com.example.elderlycare2.data.remote.response.AuthResponse
import com.example.elderlycare2.utils.ApiResult
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val sessionManager: SessionManager
) {
    suspend fun loginUser(email: String, password: String): ApiResult<AuthResponse> {
        return try {
            val response = api.loginUser(LoginRequest(email, password))
            if (response.isSuccessful && response.body() != null) {
                val authData = response.body()!!
                sessionManager.saveToken(authData.token)
                sessionManager.saveRole(authData.role)
                ApiResult.Success(authData)
            } else {
                ApiResult.Error("Login failed: ${response.message()}")
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Network error")
        }
    }

    suspend fun signUpUser(request: SignUpRequest): ApiResult<AuthResponse> {
        return try {
            val response = api.signUpUser(request)
            if (response.isSuccessful && response.body() != null) {
                ApiResult.Success(response.body()!!)
            } else {
                ApiResult.Error("Signup failed: ${response.message()}")
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Network error")
        }
    }

    fun getToken(): String? = sessionManager.getToken()
    fun getRole(): String? = sessionManager.getRole()
    fun clearSession() = sessionManager.clearSession()
}