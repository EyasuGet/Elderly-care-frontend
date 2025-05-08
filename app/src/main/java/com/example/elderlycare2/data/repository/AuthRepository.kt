package com.example.elderlycare2.data.repository

import android.content.Context
import com.example.elderlycare2.data.remote.api.AuthApi
import com.example.elderlycare2.data.remote.api.request.LoginRequest
import com.example.elderlycare2.data.remote.api.request.SignUpRequest
import com.example.elderlycare2.data.remote.response.AuthResponse
import com.example.elderlycare2.utils.ApiResult
import javax.inject.Inject

// data/repository/AuthRepository.kt
class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val context: Context // For SharedPreferences
) {
    private val sharedPrefs = context.getSharedPreferences("ElderlyCarePrefs", Context.MODE_PRIVATE)

    suspend fun loginUser(email: String, password: String): ApiResult<AuthResponse> {
        return try {
            val response = api.loginUser(LoginRequest(email, password))
            if (response.isSuccessful) {
                // Save token and role
                sharedPrefs.edit()
                    .putString("JWT_TOKEN", response.body()!!.token)
                    .putString("USER_ROLE", response.body()!!.role)
                    .apply()
                ApiResult.Success(response.body()!!)
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
            if (response.isSuccessful) {
                ApiResult.Success(response.body()!!)
            } else {
                ApiResult.Error("Signup failed: ${response.message()}")
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Network error")
        }
    }
    private fun saveToken(token: String) {
        context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
            .edit()
            .putString("JWT_TOKEN", token)
            .apply()
    }

    fun getToken(): String? = sharedPrefs.getString("JWT_TOKEN", null)
    fun getRole(): String? = sharedPrefs.getString("USER_ROLE", null)
}