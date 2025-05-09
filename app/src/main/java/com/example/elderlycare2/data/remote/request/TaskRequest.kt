package com.example.elderlycare2.data.remote.request

data class TaskRequest(
    val schedule: String, // "InPerson", "Remote"
    val frequency: String, // "Daily", "Weekly"
    val startTime: String, // ISO format: "2025-03-06T08:00:00Z"
    val endTime: String,
    val assignedTo: List<String> // User IDs
)