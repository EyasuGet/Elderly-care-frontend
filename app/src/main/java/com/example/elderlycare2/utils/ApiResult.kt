package com.example.elderlycare2.utils

// utils/ApiResult.kt
sealed class ApiResult<out T> {
    object Idle : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
}
