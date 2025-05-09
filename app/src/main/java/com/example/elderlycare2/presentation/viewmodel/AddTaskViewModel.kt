package com.example.elderlycare2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycare2.data.remote.request.TaskRequest
import com.example.elderlycare2.data.repository.TaskRepository
import com.example.elderlycare2.presentation.state.AddTaskState
import com.example.elderlycare2.presentation.state.AddTaskUiEvent
import com.example.elderlycare2.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddTaskState())
    val uiState: StateFlow<AddTaskState> = _uiState.asStateFlow()

    init {
        loadUsers()
    }

    fun onEvent(event: AddTaskUiEvent) {
        when (event) {
            is AddTaskUiEvent.IsScheduleUpdated -> _uiState.update { it.copy(schedule = event.schedule) }
            is AddTaskUiEvent.IsFrequencyUpdated -> _uiState.update { it.copy(frequency = event.frequency) }
            is AddTaskUiEvent.IsStartTimeUpdated -> _uiState.update { it.copy(startTime = event.startTime) }
            is AddTaskUiEvent.IsEndTimeUpdated -> _uiState.update { it.copy(endTime = event.endTime) }
            is AddTaskUiEvent.SelectUser -> _uiState.update { it.copy(selectedUser = event.user) }
            is AddTaskUiEvent.UpdateAssignedTo -> _uiState.update { it.copy(assignedTo = event.assignedTo) }
            AddTaskUiEvent.SubmitTask -> submitTask()
        }
    }

    private fun loadUsers() {
        viewModelScope.launch {
            taskRepository.getUsers().collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                assignedTo = result.data, // Update the list of all users
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                }
            }
        }
    }

    private fun submitTask() {
        val state = _uiState.value
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, isSuccess = false) }

            if (state.selectedUser != null) {
                // Send task to a specific user
                val taskRequest = TaskRequest(
                    schedule = state.schedule,
                    frequency = state.frequency,
                    startTime = state.startTime,
                    endTime = state.endTime,
                    assignedTo = listOf(state.selectedUser._id) // Send to the selected user's ID
                )
                sendTask(taskRequest)
            } else {
                // Send task to all users by iterating through the list
                state.assignedTo.forEach { user ->
                    val taskRequest = TaskRequest(
                        schedule = state.schedule,
                        frequency = state.frequency,
                        startTime = state.startTime,
                        endTime = state.endTime,
                        assignedTo = listOf(user._id) // Send to each user's ID
                    )
                    sendTask(taskRequest)
                }
            }
        }
    }

    private suspend fun sendTask(taskRequest: TaskRequest) {
        taskRepository.addTask(taskRequest).collect { result ->
            when (result) {
                is NetworkResult.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            error = null
                        )
                    }
                }
                is NetworkResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = false,
                            error = result.message
                        )
                    }
                }
                is NetworkResult.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}