package com.example.elderlycare2.data.repository

//import com.example.elderlycare2.data.api.ApiService
//import com.example.elderlycare2.data.remote.request.LoginRequest
//import com.example.elderlycare2.data.remote.request.SignUpRequest
//import javax.inject.Inject
//
//class UserRepository @Inject constructor(
//    private val apiService: ApiService
//) {
//
//    suspend fun login(email: String, password: String) = runCatching {
//        val loginRequest = LoginRequest(email, password)
//        apiService.login(loginRequest)
//    }
//
//    suspend fun signupU(email: String, password: String, name: String) = runCatching {
//        val signupRequest = SignUpRequest(email, password, name)
//        apiService.signUpUser(signupRequest)
//    }
//
//    suspend fun signupN(email: String, password: String, name: String) = runCatching {
//        val signupRequest = SignUpRequest(email, password, name)
//        apiService.signUpNurse(signupRequest)
//    }
//}
