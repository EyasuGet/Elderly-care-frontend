package com.example.elderlycare2.data.remote.response

import com.google.gson.annotations.SerializedName

data class HealthMetricsResponse(
    @SerializedName("heartRate") val heartRate: String,
    @SerializedName("sugarLevel") val sugarLevel: Int,
    @SerializedName("bloodPressure") val bloodPressure: String,
    @SerializedName("bloodType") val bloodType: String,
    @SerializedName("description") val description: String
)
