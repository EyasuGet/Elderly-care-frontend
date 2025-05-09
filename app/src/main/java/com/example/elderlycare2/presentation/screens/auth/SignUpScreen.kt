package com.example.elderlycare2.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.elderlycare2.data.model.Role
import com.example.elderlycare2.presentation.viewmodel.AuthViewModel
import com.example.elderlycare2.utils.NetworkResult

@Composable
fun SignUpScreen(authViewModel: AuthViewModel = viewModel(), onLoginClick: () -> Unit) {
    val signupResult by authViewModel.signupResult.observeAsState()
    val fullName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val role = remember { mutableStateOf(Role.USER) } // Default role is USER

    val backgroundColor = Color(0xFFCAE7E5)
    val primaryColor = Color(0xFF1C6B66)
    val white = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Title
        Text(
            text = "Sign Up",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Role Selector
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            RoleToggleButton("User", role.value == Role.USER) { role.value = Role.USER }
            RoleToggleButton("Nurse", role.value == Role.NURSE) { role.value = Role.NURSE }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input Fields
        TextFieldLabel("Full Name*")
        SignUpTextField("Enter your name", fullName.value) { fullName.value = it }

        TextFieldLabel("Email*")
        SignUpTextField("Enter your email", email.value) { email.value = it }

        TextFieldLabel("Password*")
        SignUpTextField("Enter your password", password.value, isPassword = true) {
            password.value = it
        }

        TextFieldLabel("Confirm Password*")
        SignUpTextField("Re-enter your password", confirmPassword.value, isPassword = true) {
            confirmPassword.value = it
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Submit Button
        Button(
            onClick = {
                if (password.value == confirmPassword.value) {
                    // Call the shared signUp method in AuthViewModel
                    authViewModel.signUp(
                        email = email.value,
                        password = password.value,
                        name = fullName.value,
                        role = role.value
                    )
                } else {
                    // Handle password mismatch
                    authViewModel.clearAuthResults()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Create Account", color = white, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Login Prompt
        Row {
            Text("Already have an account? ")
            Text(
                "Login",
                color = primaryColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onLoginClick() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Handle Results
        when (signupResult) {
            is NetworkResult.Success -> {
                Text(
                    text = "Sign up successful!",
                    color = Color.Green,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            is NetworkResult.Error -> {
                Text(
                    text = (signupResult as NetworkResult.Error).message,
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            is NetworkResult.Loading -> {
                CircularProgressIndicator(color = Color.Blue)
            }
            null -> { /* Do nothing */ }
        }
    }
}

@Composable
fun RoleToggleButton(text: String, selected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (selected) Color(0xFF1C6B66) else Color.White
    val textColor = if (selected) Color.White else Color.Black

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(140.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable { onClick() }
    ) {
        Text(text, color = textColor, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun TextFieldLabel(label: String) {
    Text(
        text = label,
        color = Color.Black,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
fun SignUpTextField(
    label: String,
    value: String,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {
    val visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(label) },
        visualTransformation = visualTransformation,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF1C6B66),
            unfocusedBorderColor = Color.LightGray,
            cursorColor = Color(0xFF1C6B66),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        )
    )
}