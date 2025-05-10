package com.example.elderlycare2.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.elderlycare2.R
import com.example.elderlycare2.presentation.state.LoginEvent
import com.example.elderlycare2.presentation.viewmodel.LoginViewModel
import com.example.elderlycare2.ui.theme.BackgroundColor
import com.example.elderlycare2.ui.theme.BorderFocusedColor
import com.example.elderlycare2.ui.theme.BorderUnfocusedColor
import com.example.elderlycare2.ui.theme.ButtonTextColor
import com.example.elderlycare2.ui.theme.PrimaryColor
import com.example.elderlycare2.ui.theme.TextColor
import com.example.elderlycare2.ui.theme.TextFieldBackground

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onForgotPassword: () -> Unit = {},
    onSignUp: () -> Unit = {}
) {
    val loginState by loginViewModel.loginState.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Listen for login result and navigate based on role
    LaunchedEffect(loginState.isSuccess, loginState.role) {
        if (loginState.isSuccess) {
            when (loginState.role) {
                "admin" -> navController.navigate("admin_home") { popUpTo("login") { inclusive = true } }
                "nurse" -> navController.navigate("nurse_home") { popUpTo("login") { inclusive = true } }
                "user"  -> navController.navigate("user_home") { popUpTo("login") { inclusive = true } }
                else    -> {/* Optionally show error or default */}
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Welcome Back",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = TextColor
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Welcome back! Please enter your details.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = TextColor.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.height(28.dp))

        LoginTextField("Email", "Enter your email", email) {
            email = it
            loginViewModel.handleEvent(LoginEvent.OnEmailChange(it))
        }
        LoginTextField("Password", "Enter your Password", password, isPassword = true) {
            password = it
            loginViewModel.handleEvent(LoginEvent.OnPasswordChange(it))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Forgot Password?",
                color = PrimaryColor,
                fontSize = 14.sp,
                modifier = Modifier.clickable { onForgotPassword() }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                loginViewModel.handleEvent(LoginEvent.Login(email, password))
            },
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text("Log in", color = ButtonTextColor, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row {
            Text("Don’t have an account? ", color = TextColor)
            Text(
                "Sign up",
                color = PrimaryColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onSignUp() }
            )
        }

        // Show loading or error
        if (loginState.isLoading) {
            Spacer(modifier = Modifier.height(20.dp))
            CircularProgressIndicator(color = PrimaryColor)
        }
        if (loginState.error != null) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = loginState.error ?: "",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun LoginTextField(
    label: String,
    placeholder: String,
    value: String,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {
    val visualTransformation =
        if (isPassword) PasswordVisualTransformation() else VisualTransformation.None

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = label,
            color = TextColor,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            visualTransformation = visualTransformation,
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = TextFieldBackground,
                unfocusedContainerColor = TextFieldBackground,
                focusedTextColor = TextColor,
                unfocusedTextColor = TextColor,
                focusedBorderColor = BorderFocusedColor,
                unfocusedBorderColor = BorderUnfocusedColor,
                cursorColor = TextColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
    }
}