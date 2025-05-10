package com.example.elderlycare2.data.remote.response

data class LoginResponse(
    val message: String,
    val token: String,
    val role: String
)