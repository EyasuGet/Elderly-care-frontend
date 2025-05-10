package com.example.elderlycare2.data.remote.request

data class TaskRequest(
    val schedule: String,
    val frequency: String,
    val startTime: String,
    val endTime: String,
    val assignedTo: List<String>
)