package com.example.elderlycare2.data.api

import com.example.elderlycare2.data.remote.request.LoginRequest
import com.example.elderlycare2.data.remote.request.SignUpRequest
import com.example.elderlycare2.data.remote.response.LoginResponse
import com.example.elderlycare2.data.remote.response.SignupResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/user/signup")
    suspend fun signUpUser(@Body request: SignUpRequest): SignupResponse

    @POST("nurse/signup")
    suspend fun signUpNurse(@Body request: SignUpRequest):SignupResponse

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
