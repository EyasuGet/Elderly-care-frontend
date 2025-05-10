package com.example.elderlycare2.data.repository
//
//import com.example.elderlycare2.data.api.ApiService
//import com.example.elderlycare2.data.model.Role
//import com.example.elderlycare2.data.remote.request.LoginRequest
//import com.example.elderlycare2.data.remote.request.SignUpRequest
//import com.example.elderlycare2.utils.SessionManager
//import javax.inject.Inject
//
//class AuthRepository @Inject constructor(
//    private val apiService: ApiService,
//    private val sessionManager: SessionManager
//) {
//
//    fun getToken(): String? {
//        return sessionManager.getAuthToken()
//    }
//
//    // Retrieve the role from SessionManager
//    fun getRole(): String? {
//        return sessionManager.getUserRole()
//    }
//
//    // Login method to authenticate the user
//    suspend fun login(email: String, password: String): Result<String> {
//        return try {
//            val response = apiService.login(LoginRequest(email, password))
//            sessionManager.saveAuthToken(response.token) // Save auth token in session
//            Result.success(response.token)
//        } catch (e: Exception) {
//            Result.failure(e) // Return failure result for exceptions
//        }
//    }
//
//    // Unified sign-up method that handles both USER and NURSE roles
//    suspend fun signUp(email: String, password: String, name: String, role: Role): Result<Unit> {
//        return try {
//            val signUpRequest = SignUpRequest(email, password, name)
//            when (role) {
//                Role.USER -> apiService.signUpUser(signUpRequest) // Call user sign-up endpoint
//                Role.NURSE -> apiService.signUpNurse(signUpRequest) // Call nurse sign-up endpoint
//            }
//            Result.success(Unit)
//        } catch (e: Exception) {
//            Result.failure(e) // Return failure result for exceptions
//        }
//    }
//
//    // Logout method to clear session data
//    fun logout() {
//        sessionManager.clearSession()
//    }
// }