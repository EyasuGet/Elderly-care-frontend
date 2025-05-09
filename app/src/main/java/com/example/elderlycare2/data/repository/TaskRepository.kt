package com.example.elderlycare2.data.repository

import com.example.elderlycare2.data.api.TasksApi
import com.example.elderlycare2.data.remote.request.TaskRequest
import com.example.elderlycare2.data.remote.response.TaskResponse
import com.example.elderlycare2.data.remote.response.User
import com.example.elderlycare2.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val tasksApi: TasksApi
) {

    // Fetch all users
    suspend fun getUsers(): Flow<NetworkResult<List<User>>> = flow {
        emit(NetworkResult.Loading())
        try {
            val response = tasksApi.getUsers()
            if (response.isSuccessful && response.body() != null) {
                emit(NetworkResult.Success(response.body()!!))
            } else {
                emit(NetworkResult.Error("Failed to fetch users: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "An unexpected error occurred"))
        }
    }

    // Add a task
    suspend fun addTask(taskRequest: TaskRequest): Flow<NetworkResult<TaskResponse>> = flow {
        emit(NetworkResult.Loading())
        try {
            val response = tasksApi.addTask(taskRequest)
            if (response.isSuccessful && response.body() != null) {
                emit(NetworkResult.Success(response.body()!!))
            } else {
                emit(NetworkResult.Error("Failed to add task: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "An unexpected error occurred"))
        }
    }
}