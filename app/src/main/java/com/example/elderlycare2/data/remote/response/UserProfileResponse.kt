package com.example.elderlycare2.data.remote.response

data class UserProfileResponse(
    val heartRate: String?,
    val sugarLevel: String?,
    val bloodPressure: String?,
    val bloodType: String?,
    val description: String?,
    val _id: String,
    val name: String,
    val id: String,
    val password: String,
    val email: String,
    val caretaker: String,
    val gender: String,
    val phoneNo: String,
    val address: String,
    val profileImg: String?,
    val tasks: List<String>,
    val role: String,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int
)