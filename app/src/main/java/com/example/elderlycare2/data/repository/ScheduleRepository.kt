package com.example.elderlycare2.data.repository

import com.example.elderlycare2.data.api.ScheduleApi
import com.example.elderlycare2.data.remote.response.TaskResponse
import com.example.elderlycare2.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ScheduleRepository @Inject constructor(
    private val scheduleApi: ScheduleApi
) {

    fun getTasks(): Flow<NetworkResult<List<TaskResponse>>> = flow {
        emit(NetworkResult.Loading())
        try {
            val response = scheduleApi.getTasks()
            emit(NetworkResult.Success(response.body()!!))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "Network error"))
        }
    }
}