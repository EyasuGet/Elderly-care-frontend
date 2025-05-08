package com.example.elderlycare2.data.repository

import com.example.elderlycare2.data.remote.api.NurseApi
import com.example.elderlycare2.data.remote.api.request.TaskRequest
import com.example.elderlycare2.data.remote.response.TaskResponse
import com.example.elderlycare2.data.remote.response.User
import com.example.elderlycare2.data.remote.response.HealthMetricsResponse
import com.example.elderlycare2.data.remote.api.request.HealthUpdateRequest
import com.example.elderlycare2.utils.ApiResult
import javax.inject.Inject

class NurseRepository @Inject constructor(
    private val api: NurseApi
) {
    // Get list of assigned elders
    suspend fun getAssignedElders(token: String): ApiResult<List<User>> {
        return try {
            val response = api.getAssignedUsers("Bearer $token")
            if (response.isSuccessful) {
                ApiResult.Success(response.body() ?: emptyList())
            } else {
                ApiResult.Error("Failed to fetch elders: ${response.code()}")
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Network error")
        }
    }

    // Add a new task for elders
    suspend fun addTask(
        token: String,
        task: TaskRequest
    ): ApiResult<TaskResponse> {
        return try {
            val response = api.addTask("Bearer $token", task)
            if (response.isSuccessful) {
                ApiResult.Success(response.body()!!)
            } else {
                ApiResult.Error("Failed to add task: ${response.code()}")
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Network error")
        }
    }

    // Get elder's health metrics
    suspend fun getHealthMetrics(
        token: String,
        userId: String
    ): ApiResult<HealthMetricsResponse> {
        return try {
            val response = api.getUserDetails("Bearer $token", userId)
            if (response.isSuccessful) {
                ApiResult.Success(response.body()!!)
            } else {
                ApiResult.Error("Failed to fetch health data: ${response.code()}")
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Network error")
        }
    }

    // Update health metrics
    suspend fun updateHealthMetrics(
        token: String,
        userId: String,
        metrics: HealthUpdateRequest
    ): ApiResult<HealthMetricsResponse> {
        return try {
            val response = api.updateUserDetails("Bearer $token", userId, metrics)
            if (response.isSuccessful) {
                ApiResult.Success(response.body()!!)
            } else {
                ApiResult.Error("Update failed: ${response.code()}")
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Network error")
        }
    }
}