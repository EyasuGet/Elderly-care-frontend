package com.example.elderlycare2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.elderlycare2.data.repository.AuthRepository
import com.example.elderlycare2.presentation.screens.auth.LoginScreen
import com.example.elderlycare2.presentation.screens.nurse.NurseHomeScreen

// navigation/AppNav.kt
@Composable
fun AppNav(initialToken: String?) {
    val navController = rememberNavController()
    LaunchedEffect(initialToken) {
        println("Initial token: $initialToken") // 👈 Check Logcat
        if (initialToken != null) {
            navController.navigate(Routes.LANDING)
        }
    }
    NavHost(navController, startDestination = if (initialToken != null) Routes.NURSE_HOME else Routes.LOGIN) {
        composable(Routes.LOGIN) {
            LoginScreen(onLoginSuccess = { navController.navigate(Routes.NURSE_HOME) })
        }
        composable(Routes.NURSE_HOME) {
            NurseHomeScreen(token = initialToken!!)
        }
    }
}

//@Composable
//fun AppNav(initialToken: String?) {
//    val navController = rememberNavController()
//    LaunchedEffect(initialToken) {
//        println("Initial token: $initialToken") // 👈 Check Logcat
//        if (initialToken != null) {
//            navController.navigate(Routes.HOME)
//        }
//    }
//    // ... rest of your NavHost
//}