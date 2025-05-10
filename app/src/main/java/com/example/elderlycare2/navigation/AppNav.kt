package com.example.elderlycare2.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.elderlycare2.presentation.screens.auth.LandingPage
import com.example.elderlycare2.presentation.screens.auth.LoginScreen
import com.example.elderlycare2.presentation.screens.auth.SignUpScreen
import com.example.elderlycare2.presentation.screens.user.UserHomeScreen

@Composable
fun AppNav(initialToken: String?) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "landing"
    ) {
        composable("landing") {
            LandingPage(
                onGetStarted = { navController.navigate("signup") },
                onLogin = { navController.navigate("login") }
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
        composable("user_home") {
            UserHomeScreen(navController = navController)
        }
//        composable("nurse_home") {
//            NurseHomeScreen(navController = navController)
//        }
    }
}