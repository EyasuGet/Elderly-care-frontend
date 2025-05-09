package com.example.elderlycare2.data.repository

import com.example.elderlycare2.data.api.UserProfileApi
import com.example.elderlycare2.data.remote.request.UserEditProfileRequest
import com.example.elderlycare2.data.remote.response.UserProfileResponse
import com.example.elderlycare2.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserProfileRepository @Inject constructor(
    private val userProfileApi: UserProfileApi
) {

    suspend fun getUserProfile(): Flow<NetworkResult<UserProfileResponse>> = flow {
        emit(NetworkResult.Loading())
        try {
            val response = userProfileApi.getUserProfile()
            if (response.isSuccessful && response.body() != null) {
                emit(NetworkResult.Success(response.body()!!))
            } else {
                emit(NetworkResult.Error("Failed to fetch user profile: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "An unexpected error occurred"))
        }
    }

    suspend fun updateUserProfile(request: UserEditProfileRequest): Flow<NetworkResult<Unit>> = flow {
        emit(NetworkResult.Loading())
        try {
            val response = userProfileApi.updateUserProfile(request)
            if (response.isSuccessful) {
                emit(NetworkResult.Success(Unit))
            } else {
                emit(NetworkResult.Error("Failed to update profile: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "An unexpected error occurred"))
        }
    }
}