package com.example.elderlycare2.data.remote.response

import com.google.gson.annotations.SerializedName

// AuthResponse.kt
data class AuthResponse(
    @SerializedName("token") val token: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("role") val role: String // "user" or "nurse"
)