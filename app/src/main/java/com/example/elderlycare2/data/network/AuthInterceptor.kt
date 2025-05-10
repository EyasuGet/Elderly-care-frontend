package com.example.elderlycare2.data.network

import com.example.elderlycare2.data.storage.LocalStorage
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val localStorage: LocalStorage) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = localStorage.getToken()
        val request = if (token != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }
        return chain.proceed(request)
    }
}