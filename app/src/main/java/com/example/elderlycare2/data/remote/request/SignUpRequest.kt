package com.example.elderlycare2.data.remote.request

import com.example.elderlycare2.data.model.Role

data class SignUpRequest(
    val name: String,
    val email: String,
    val password: String,
    val role: Role
)