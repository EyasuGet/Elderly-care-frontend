package com.example.elderlycare2.data.remote.request

import com.google.gson.annotations.SerializedName

// HealthUpdateRequest.kt
data class HealthUpdateRequest(
    @SerializedName("heartRate") val heartRate: String,
    @SerializedName("sugarLevel") val sugarLevel: Int,
    @SerializedName("bloodPressure") val bloodPressure: String,
    @SerializedName("bloodType") val bloodType: String,
    @SerializedName("description") val description: String
)