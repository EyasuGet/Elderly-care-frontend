package com.example.elderlycare2.data.remote.api

import com.example.elderlycare2.data.remote.api.request.LoginRequest
import com.example.elderlycare2.data.remote.api.request.SignUpRequest
import com.example.elderlycare2.data.remote.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/user/signup")
    suspend fun signUpUser(@Body request: SignUpRequest): Response<AuthResponse>

    @POST("auth/nurse/signup")
    suspend fun signUpNurse(@Body request: SignUpRequest): Response<AuthResponse>

    @POST("auth/user/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<AuthResponse>
}
