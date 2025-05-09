package com.example.elderlycare2.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycare2.data.remote.request.HealthUpdateRequest
import com.example.elderlycare2.data.remote.request.TaskRequest
import com.example.elderlycare2.data.remote.response.HealthMetricsResponse
import com.example.elderlycare2.data.remote.response.TaskResponse
import com.example.elderlycare2.data.remote.response.User
import com.example.elderlycare2.data.repository.NurseRepository
import com.example.elderlycare2.utils.ApiResult
import com.example.elderlycare2.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NurseViewModel @Inject constructor(
    private val repo: NurseRepository
) : ViewModel() {

    // Elders List State

    private val _eldersState = MutableStateFlow<ApiResult<List<User>>>(ApiResult.Idle)
    val eldersState: StateFlow<ApiResult<List<User>>> = _eldersState


    // Task Creation State
    private val _taskState = MutableStateFlow<ApiResult<TaskResponse>>(ApiResult.Idle)
    val taskState: StateFlow<ApiResult<TaskResponse>> = _taskState

    // Health Metrics State
    private val _healthMetricsState = mutableStateOf<ApiResult<HealthMetricsResponse>>(ApiResult.Idle)
    val healthMetricsState: State<ApiResult<HealthMetricsResponse>> = _healthMetricsState
    // Load list of assigned elders
    fun loadElders(token: String) {
        viewModelScope.launch {
            repo.getAssignedElders(token).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        _eldersState.value = ApiResult.Loading
                    }
                    is NetworkResult.Success -> {
                        _eldersState.value = ApiResult.Success(result.data)
                    }
                    is NetworkResult.Error -> {
                        _eldersState.value = ApiResult.Error(result.message)
                    }
                }
            }
        }
    }

    // Add a new task
    fun addTask(token: String, task: TaskRequest) {
        viewModelScope.launch {
            repo.addTask(token, task).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        _taskState.value = ApiResult.Loading
                    }
                    is NetworkResult.Success -> {
                        _taskState.value = ApiResult.Success(result.data)
                    }
                    is NetworkResult.Error -> {
                        _taskState.value = ApiResult.Error(result.message)
                    }
                }
            }
        }
    }

    // Get health metrics for a specific elder
    fun loadHealthMetrics(token: String, userId: String) {
        viewModelScope.launch {
            repo.getHealthMetrics(token, userId).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        _healthMetricsState.value = ApiResult.Loading
                    }
                    is NetworkResult.Success -> {
                        _healthMetricsState.value = ApiResult.Success(result.data)
                    }
                    is NetworkResult.Error -> {
                        _healthMetricsState.value = ApiResult.Error(result.message)
                    }
                }
            }
        }
    }

    // Update health metrics
    fun updateHealthMetrics(token: String, userId: String, metrics: HealthUpdateRequest) {
        viewModelScope.launch {
            repo.updateHealthMetrics(token, userId, metrics).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        _healthMetricsState.value = ApiResult.Loading
                    }
                    is NetworkResult.Success -> {
                        _healthMetricsState.value = ApiResult.Success(result.data)
                    }
                    is NetworkResult.Error -> {
                        _healthMetricsState.value = ApiResult.Error(result.message)
                    }
                }
            }
        }
    }

    // Reset states when needed
    fun resetTaskState() {
        _taskState.value = ApiResult.Idle
    }

    fun resetHealthMetricsState() {
        _healthMetricsState.value = ApiResult.Idle
    }}