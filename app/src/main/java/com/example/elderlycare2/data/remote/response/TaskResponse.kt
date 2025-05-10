package com.example.elderlycare2.data.remote.response

data class TaskResponse(
    val id: String,
    val schedule: String,
    val startTime: String,
    val endTime: String,
    val frequency: String,
    val done: Boolean,
    val assignedTo: List<String>,
)