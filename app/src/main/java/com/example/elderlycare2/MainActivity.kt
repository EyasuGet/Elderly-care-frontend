package com.example.elderlycare2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.elderlycare2.navigation.AppNav
import com.example.elderlycare2.presentation.viewmodel.SessionViewModel
import com.example.elderlycare2.ui.theme.ElderlyCareTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ElderlyCareTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainAppContent()
                }
            }
        }
    }
}

@Composable
fun MainAppContent(
    sessionViewModel: SessionViewModel = hiltViewModel()
) {
    // Observe the token from the SessionViewModel
    val token = sessionViewModel.token.observeAsState()

    // Pass the token to the AppNav
    AppNav(initialToken = token.value)
}