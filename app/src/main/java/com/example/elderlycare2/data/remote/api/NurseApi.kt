package com.example.elderlycare2.data.remote.api

import com.example.elderlycare2.data.remote.api.request.HealthUpdateRequest
import com.example.elderlycare2.data.remote.api.request.TaskRequest
import com.example.elderlycare2.data.remote.response.HealthMetricsResponse
import com.example.elderlycare2.data.remote.response.TaskResponse
import com.example.elderlycare2.data.remote.response.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.Response

// data/remote/api/NurseApi.kt
interface NurseApi {
    // Get elder's health details
    @GET("nurse/user/{userId}/details")
    suspend fun getUserDetails(
        @Header("Authorization") token: String,
        @Path("userId") userId: String
    ): Response<HealthMetricsResponse>

    // Update elder's health metrics
    @PUT("nurse/user/{userId}/details")
    suspend fun updateUserDetails(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Body request: HealthUpdateRequest
    ): Response<HealthMetricsResponse>

    // Other existing endpoints
    @GET("nurse/")
    suspend fun getAssignedUsers(
        @Header("Authorization") token: String
    ): Response<List<User>>

    @POST("nurse/addTask")
    suspend fun addTask(
        @Header("Authorization") token: String,
        @Body task: TaskRequest
    ): Response<TaskResponse>
}