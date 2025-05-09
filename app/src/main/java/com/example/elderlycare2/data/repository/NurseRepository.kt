package com.example.elderlycare2.data.repository

import com.example.elderlycare2.data.api.NurseApi
import com.example.elderlycare2.data.remote.request.HealthUpdateRequest
import com.example.elderlycare2.data.remote.request.TaskRequest
import com.example.elderlycare2.data.remote.response.HealthMetricsResponse
import com.example.elderlycare2.data.remote.response.TaskResponse
import com.example.elderlycare2.data.remote.response.User
import com.example.elderlycare2.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NurseRepository @Inject constructor(
    private val api: NurseApi
) {
    // Get list of assigned elders
    fun getAssignedElders(token: String): Flow<NetworkResult<List<User>>> = flow {
        emit(NetworkResult.Loading())
        try {
            val response = api.getAssignedUsers("Bearer $token")
            if (response.isSuccessful) {
                emit(NetworkResult.Success(response.body() ?: emptyList()))
            } else {
                emit(NetworkResult.Error("Failed to fetch elders: ${response.code()}"))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "Network error"))
        }
    }

    // Add a new task for elders
    fun addTask(token: String, task: TaskRequest): Flow<NetworkResult<TaskResponse>> = flow {
        emit(NetworkResult.Loading())
        try {
            val response = api.addTask("Bearer $token", task)
            if (response.isSuccessful) {
                emit(NetworkResult.Success(response.body()!!))
            } else {
                emit(NetworkResult.Error("Failed to add task: ${response.code()}"))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "Network error"))
        }
    }

    // Get elder's health metrics
    fun getHealthMetrics(token: String, userId: String): Flow<NetworkResult<HealthMetricsResponse>> = flow {
        emit(NetworkResult.Loading())
        try {
            val response = api.getUserDetails("Bearer $token", userId)
            if (response.isSuccessful) {
                emit(NetworkResult.Success(response.body()!!))
            } else {
                emit(NetworkResult.Error("Failed to fetch health data: ${response.code()}"))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "Network error"))
        }
    }

    // Update health metrics
    fun updateHealthMetrics(
        token: String,
        userId: String,
        metrics: HealthUpdateRequest
    ): Flow<NetworkResult<HealthMetricsResponse>> = flow {
        emit(NetworkResult.Loading())
        try {
            val response = api.updateUserDetails("Bearer $token", userId, metrics)
            if (response.isSuccessful) {
                emit(NetworkResult.Success(response.body()!!))
            } else {
                emit(NetworkResult.Error("Update failed: ${response.code()}"))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "Network error"))
        }
    }
}