package com.example.elderlycare2.data.repository

import com.example.elderlycare2.data.api.SignupApi
import com.example.elderlycare2.data.remote.request.SignUpRequest
import com.example.elderlycare2.data.remote.response.SignupResponse

class SignupRepository(private val signupApi: SignupApi) {

    // Function to call the user signup API
    suspend fun signUpUser(email: String, password: String, name: String): SignupResponse {
        val signupRequest = SignUpRequest(email = email, password = password, name = name)
        return signupApi.signUpUser(signupRequest)
    }

    // Function to call the nurse signup API
    suspend fun signUpNurse(email: String, password: String, name: String): SignupResponse {
        val signupRequest = SignUpRequest(email = email, password = password, name = name)
        return signupApi.signUpNurse(signupRequest)
    }
}