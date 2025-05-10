package com.example.elderlycare2.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.elderlycare2.presentation.screens.auth.LoginScreen
import com.example.elderlycare2.presentation.screens.auth.SignUpScreen
import com.example.elderlycare2.presentation.screens.auth.LandingPage
@Composable
fun AppNav(initialToken: String?) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (initialToken != null) "home" else "landing"
    ) {
        composable("landing") {
            LandingPage(
                onGetStarted = {
                    navController.navigate("signup")
                }
            )
        }
        composable("signup") {
            SignUpScreen(
                signupViewModel = hiltViewModel(),
                onLoginClick = { navController.navigate("login") }
            )
        }
        composable("login") {
            LoginScreen(
                navController = navController,
                onSignUp = { navController.navigate("signup") }
            )
        }
        // Add your home/admin/nurse/user screens here as needed:
        // composable("home") { HomeScreen() }
//         composable("nurse_home") { NurseHomeScreen() }

    }
}