package com.example.elderlycare2.data.api

import com.example.elderlycare2.data.remote.request.LoginRequest
import com.example.elderlycare2.data.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}