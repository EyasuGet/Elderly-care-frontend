package com.example.elderlycare2.presentation.screens.nurse

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.elderlycare2.data.remote.response.User
import com.example.elderlycare2.presentation.viewmodel.NurseViewModel
import com.example.elderlycare2.utils.ApiResult


@Composable
fun NurseHomeScreen(
    viewModel: NurseViewModel = hiltViewModel(),
    token: String
) {
    val eldersState by viewModel.eldersState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadElders(token)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        when (val state = eldersState) {
            is ApiResult.Success-> {
                val userList = state.data as? List<User> ?: emptyList()
                LazyColumn {
                    items(userList) { elder ->
                        ElderCard(elder) {
                            // Navigate to elder details
                        }
                    }
                }
            }
            is ApiResult.Error -> {
                Text(state.message, color = Color.Red)
            }
            ApiResult.Loading -> {
                CircularProgressIndicator()
            }
            else -> {}
        }
    }
}

@Composable
fun ElderCard(elder: User, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = elder.name, fontWeight = FontWeight.Bold)
            Text(text = elder.email)
        }
    }
}
