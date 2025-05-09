package com.example.elderlycare2.presentation.screens.nurse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.elderlycare2.data.remote.request.TaskRequest
import com.example.elderlycare2.presentation.viewmodel.NurseViewModel
import com.example.elderlycare2.utils.ApiResult

@Composable
fun NurseTaskScreen(viewModel: NurseViewModel, token: String) {
    val taskState by viewModel.taskState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        when (val state = taskState) {
            is ApiResult.Success -> {
                Text("Task created for ${state.data.assignedTo.size} users!")
            }
            is ApiResult.Error -> {
                Text("Error: ${state.message}", color = Color.Red)
            }
            ApiResult.Loading -> {
                CircularProgressIndicator()
            }
            ApiResult.Idle -> {
                // Initial state
            }
        }

        Button(onClick = {
            viewModel.addTask(token, TaskRequest(
                schedule = "InPerson",
                frequency = "Daily",
                startTime = "2025-03-06T08:00:00Z",
                endTime = "2025-03-07T08:00:00Z",
                assignedTo = listOf("user123") // Replace with dynamic user IDs
            )
            )
        }) {
            ("Assign Task")
        }
    }
}

