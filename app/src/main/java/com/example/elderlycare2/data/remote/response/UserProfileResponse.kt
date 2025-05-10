package com.example.elderlycare2.data.remote.response

data class UserProfileResponse(
    val _id: String,
    val name: String,
    val id: String,
    val password: String,
    val email: String,
    val caretaker: String,
    val gender: String,
    val phoneNo: String,
    val address: String,
)