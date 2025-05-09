package com.example.elderlycare2.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message")
    val message: String, // The success message for login

    @SerializedName("token")
    val token: String, // The authentication token

    @SerializedName("role")
    val role: String // The role of the user (e.g., "user" or "nurse")
)