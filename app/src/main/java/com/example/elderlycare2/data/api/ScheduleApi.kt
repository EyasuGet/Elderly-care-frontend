package com.example.elderlycare2.data.api

import com.example.elderlycare2.data.remote.response.TasksResponse
import retrofit2.Response
import retrofit2.http.GET

interface ScheduleApi {
    @GET("user/tasks")
    suspend fun getTasks(): Response<TasksResponse>
}