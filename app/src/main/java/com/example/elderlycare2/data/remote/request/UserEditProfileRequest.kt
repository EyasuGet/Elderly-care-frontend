package com.example.elderlycare2.data.remote.request

data class UserEditProfileRequest(
    val name: String,
     val email: String? = "",
    val caretaker: String,
    val gender: String,
    val phoneNo: String,
    val address: String
)