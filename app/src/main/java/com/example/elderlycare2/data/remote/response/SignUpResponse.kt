package com.example.elderlycare2.data.remote.response

import com.google.gson.annotations.SerializedName

data class SignupResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("data")
    val data: UserData
)

data class UserData(
    @SerializedName("name")
    val name: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("caretaker")
    val caretaker: String? = null,

    @SerializedName("gender")
    val gender: String? = null,

    @SerializedName("phoneNo")
    val phoneNo: String? = null,

    @SerializedName("address")
    val address: String? = null,

    @SerializedName("profileImg")
    val profileImg: String? = null,

    @SerializedName("tasks")
    val tasks: List<String> = emptyList(),

    @SerializedName("role")
    val role: String,

    @SerializedName("heartRate")
    val heartRate: String? = null,

    @SerializedName("sugarLevel")
    val sugarLevel: String? = null,

    @SerializedName("bloodPressure")
    val bloodPressure: String? = null,

    @SerializedName("bloodType")
    val bloodType: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("_id")
    val internalId: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("__v")
    val version: Int
)