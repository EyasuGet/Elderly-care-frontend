package com.example.elderlycare2.data.remote.response

import com.google.gson.annotations.SerializedName

// TaskResponse.kt
data class TaskResponse(
    @SerializedName("_id") val id: String,
    @SerializedName("schedule") val schedule: String,
    @SerializedName("assignedTo") val assignedTo: List<String>
)