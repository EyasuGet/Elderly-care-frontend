package com.example.elderlycare2.presentation.state

import com.example.elderlycare2.data.remote.response.TaskResponse

data class TimeScheduleState(
    val isLoading: Boolean = false,
    val tasks: List<TaskResponse>? = null,
    val error: String? = null
)

sealed class TimeScheduleEvent {
    object FetchQueues : TimeScheduleEvent()
}