package com.example.elderlycare2.data.api

import com.example.elderlycare2.data.remote.request.UserEditProfileRequest
import com.example.elderlycare2.data.remote.response.UserProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserProfileApi {

    @GET("user/profile")
    suspend fun getUserProfile(): Response<UserProfileResponse>

    @PUT("user/profile")
    suspend fun updateUserProfile(@Body request: UserEditProfileRequest): Response<Unit>
}