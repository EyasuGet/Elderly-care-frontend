package com.example.elderlycare2.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.elderlycare2.presentation.screens.auth.LoginScreen
import com.example.elderlycare2.presentation.screens.auth.SignUpScreen
import com.example.elderlycare2.presentation.screens.auth.landingPage.LandingPage

@Composable
fun AppNav(initialToken: String?) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (initialToken != null) "home" else "landing"
    )

    {
            composable("landing") {
                LandingPage(
                    onGetStarted = {
                        navController.navigate("signup")  // assuming "login" is a valid route
                    }
                )
            }

        composable("signup") {
            SignUpScreen(
                authViewModel = hiltViewModel(),
                onLoginClick = { navController.navigate("login") } // Navigate to LoginScreen
            )
        }
        composable("login") {
            LoginScreen(
                onLogin = { /* Handle login logic */ },
                onSignUp = { navController.navigate("signup") } // Navigate back to SignUpScreen
            )
        }
    }
}