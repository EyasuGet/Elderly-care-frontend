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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.elderlycare2.presentation.state.SignupEvent
import com.example.elderlycare2.presentation.viewmodel.SignupViewModel

@Composable
fun SignUpScreen(signupViewModel: SignupViewModel = viewModel(), onLoginClick: () -> Unit) {
    val signupState by signupViewModel.signupState.collectAsState()

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

        // Role Selector (NEW LOGIC)
        RoleSelection(signupViewModel = signupViewModel)

        Spacer(modifier = Modifier.height(16.dp))

        // Input Fields
        TextFieldLabel("Full Name*")
        SignUpTextField(
            label = "Enter Your Name",
            value = signupState.name,
            onValueChange = { signupViewModel.handleEvent(SignupEvent.OnNameChange(it)) }
        )

        TextFieldLabel("Email*")
        SignUpTextField(
            label = "Enter Your Email",
            value = signupState.email,
            onValueChange = { signupViewModel.handleEvent(SignupEvent.OnEmailChange(it)) }
        )

        TextFieldLabel("Password*")
        SignUpTextField(
            label = "Enter your password",
            value = signupState.password,
            isPassword = true,
            onValueChange = { signupViewModel.handleEvent(SignupEvent.OnPassword(it)) }
        )

        TextFieldLabel("Confirm Password*")
        SignUpTextField(
            label = "ReEnter Your password",
            value = signupState.confirmPassword,
            isPassword = true,
            onValueChange = { signupViewModel.handleEvent(SignupEvent.OnConfirmPassword(it)) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Submit Button
        Button(
            onClick = {
                if (signupState.password == signupState.confirmPassword) {
                    // Trigger signup based on role
                    signupViewModel.handleEvent(
                        when (signupState.role) {
                            "USER" -> SignupEvent.SignUpUser(signupState.email, signupState.password, signupState.name)
                            "NURSE" -> SignupEvent.SignUpNurse(signupState.email, signupState.password, signupState.name)
                            else -> throw Error("Role error")
                        }
                    )
                } else {
                    signupViewModel.handleEvent(SignupEvent.ClearSignupResults)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            if (signupState.isLoading) {
                CircularProgressIndicator(color = white, modifier = Modifier.size(24.dp))
            } else {
                Text("Create Account", color = white, fontWeight = FontWeight.Bold)
            }
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
        when {
            signupState.isSignedUp -> {
                Text(
                    text = "Sign up successful!",
                    color = Color.Green,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                signupViewModel.handleEvent(SignupEvent.ClearSignupResults)
            }
            signupState.error != null -> {
                Text(
                    text = signupState.error!!,
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                signupViewModel.handleEvent(SignupEvent.ClearSignupResults)
            }
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

@Composable
fun RoleSelection(signupViewModel: SignupViewModel) {
    val signupState by signupViewModel.signupState.collectAsState()

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        RoleToggleButton(
            text = "User",
            selected = signupState.role == "USER"
        ) {
            signupViewModel.handleEvent(SignupEvent.OnRoleChange("USER"))
        }

        RoleToggleButton(
            text = "Nurse",
            selected = signupState.role == "NURSE"
        ) {
            signupViewModel.handleEvent(SignupEvent.OnRoleChange("NURSE"))
        }
    }
}