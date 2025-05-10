package com.example.elderlycare2.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.elderlycare2.data.storage.LocalStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val localStorage: LocalStorage
) : ViewModel() {

    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?> = _token

    private val _role = MutableLiveData<String?>()
    val role: LiveData<String?> = _role

    init {
        // Load token and role when the ViewModel is initialized
        _token.value = localStorage.getToken()
        _role.value = localStorage.getRole()
    }

    fun clearSession() {
        localStorage.clearSession()
        _token.value = null
        _role.value = null
    }
}