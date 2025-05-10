package com.example.elderlycare2.data.api

import com.example.elderlycare2.data.remote.request.SignUpRequest
import com.example.elderlycare2.data.remote.response.SignupResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupApi {
    @POST("auth/user/signup")
    suspend fun signUpUser(@Body signupRequest: SignUpRequest): SignupResponse

    @POST("nurse/signup")
    suspend fun signUpNurse(@Body signupRequest: SignUpRequest): SignupResponse
}