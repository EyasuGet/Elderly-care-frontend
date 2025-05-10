package com.example.elderlycare2.data.repository

import com.example.elderlycare2.data.api.LoginAPi
import com.example.elderlycare2.data.remote.request.LoginRequest
import com.example.elderlycare2.data.remote.response.LoginResponse
import com.example.elderlycare2.data.storage.LocalStorage

class LoginRepository(
    private val loginApi: LoginAPi,
    private val localStorage: LocalStorage
) {

    suspend fun loginUser(email: String, password: String): LoginResponse {
        val loginRequest = LoginRequest(email = email, password = password)
        val response = loginApi.login(loginRequest)

        // Save token and role for session management
        response.token?.let { localStorage.saveToken(it) }
        response.role?.let { localStorage.saveRole(it) }

        return response
    }
}