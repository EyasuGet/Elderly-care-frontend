package com.example.elderlycare2.presentation.state

import com.example.elderlycare2.data.remote.response.User

data class AddTaskState(
    val schedule: String = "",
    val frequency: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val selectedUser: User? = null,
    val assignedTo: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

sealed class AddTaskUiEvent {
    data class IsScheduleUpdated(val schedule: String) : AddTaskUiEvent()
    data class IsFrequencyUpdated(val frequency: String) : AddTaskUiEvent()
    data class IsStartTimeUpdated(val startTime: String) : AddTaskUiEvent()
    data class IsEndTimeUpdated(val endTime: String) : AddTaskUiEvent()
    data class SelectUser(val user: User) : AddTaskUiEvent()
    data class UpdateAssignedTo(val assignedTo: List<User>) : AddTaskUiEvent()
    object SubmitTask : AddTaskUiEvent()
}