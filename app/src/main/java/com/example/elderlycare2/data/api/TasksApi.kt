package com.example.elderlycare2.data.api

import com.example.elderlycare2.data.remote.request.TaskRequest
import com.example.elderlycare2.data.remote.response.TaskResponse
import com.example.elderlycare2.data.remote.response.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TasksApi {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @POST("nurse/addTask")
    suspend fun addTask(@Body request: TaskRequest): Response<TaskResponse>
}