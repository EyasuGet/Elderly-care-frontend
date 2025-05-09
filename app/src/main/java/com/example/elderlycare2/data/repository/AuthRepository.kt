package com.example.elderlycare2.data.repository

import com.example.elderlycare2.data.api.ApiService
import com.example.elderlycare2.data.remote.request.LoginRequest
import com.example.elderlycare2.data.remote.request.SignUpRequest
import com.example.elderlycare2.utils.SessionManager
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService,
    private val sessionManager: SessionManager
) {

    suspend fun login(email: String, password: String): Result<String> {
        return try {
            val response = apiService.login(LoginRequest(email, password))
            sessionManager.saveAuthToken(response.token)
            Result.success(response.token)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signUpUser(email: String, password: String, name: String): Result<Unit> {
        return try {
            apiService.signUpUser(SignUpRequest(email, password, name))
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signUpNurse(email: String, password: String, name: String): Result<Unit> {
        return try {
            apiService.signUpNurse(SignUpRequest(email, password, name))
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() {
        sessionManager.clearSession()
    }
}