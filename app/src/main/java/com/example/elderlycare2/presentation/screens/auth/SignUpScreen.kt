package com.example.elderlycare2.presentation.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.elderlycare2.navigation.Routes
import com.example.elderlycare2.presentation.viewmodel.SignUpViewModel
import com.example.elderlycare2.utils.ApiResult

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val signUpState by viewModel.signUpState.collectAsState()
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Handle signup state changes
    LaunchedEffect(signUpState) {
        when (signUpState) {
            is ApiResult.Success -> {
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.SIGN_UP) { inclusive = true }
                }
            }
            is ApiResult.Error -> {
                val error = (signUpState as ApiResult.Error).message
                Toast.makeText(context, "Signup failed: $error", Toast.LENGTH_SHORT).show()
//                viewModel.resetState() // Reset state after showing error
            }
            ApiResult.Loading -> { /* Loading handled via button state */ }
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.signUp(name, email, password) },
            modifier = Modifier.fillMaxWidth(),
            enabled = signUpState !is ApiResult.Loading
        ) {
            if (signUpState is ApiResult.Loading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text("Sign Up")
            }
        }
    }
}