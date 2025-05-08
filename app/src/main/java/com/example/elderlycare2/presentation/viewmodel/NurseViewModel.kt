package com.example.elderlycare2.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycare2.data.remote.api.request.HealthUpdateRequest
import com.example.elderlycare2.data.remote.api.request.TaskRequest
import com.example.elderlycare2.data.remote.response.HealthMetricsResponse
import com.example.elderlycare2.data.remote.response.TaskResponse
import com.example.elderlycare2.data.remote.response.User
import com.example.elderlycare2.data.repository.NurseRepository
import com.example.elderlycare2.utils.ApiResult
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
            _eldersState.value = ApiResult.Loading
            _eldersState.value = repo.getAssignedElders(token)
        }
    }

    // Add a new task
    fun addTask(token: String, task: TaskRequest) {
        viewModelScope.launch {
            _taskState.value = ApiResult.Loading
            _taskState.value = repo.addTask(token, task)
        }
    }

    // Get health metrics for a specific elder
    fun loadHealthMetrics(token: String, userId: String) {
        viewModelScope.launch {
            _healthMetricsState.value = ApiResult.Loading
            _healthMetricsState.value = repo.getHealthMetrics(token, userId)
        }
    }

    // Update health metrics
    fun updateHealthMetrics(token: String, userId: String, metrics: HealthUpdateRequest) {
        viewModelScope.launch {
            _healthMetricsState.value = ApiResult.Loading
            _healthMetricsState.value = repo.updateHealthMetrics(token, userId, metrics)
        }
    }

    // Reset states when needed
    fun resetTaskState() {
        _taskState.value = ApiResult.Idle
    }

    fun resetHealthMetricsState() {
        _healthMetricsState.value = ApiResult.Idle
    }
}