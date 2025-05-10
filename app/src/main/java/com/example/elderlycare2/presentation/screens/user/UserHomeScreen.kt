package com.example.elderlycare2.presentation.screens.user

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.elderlycare2.ui.components.BottomNavBar
import com.example.elderlycare2.ui.theme.BackgroundColor
import com.example.elderlycare2.ui.theme.BackgroundColoruser
import com.example.elderlycare2.ui.theme.TextColor

@Composable
fun UserHomeScreen(navController: NavHostController,
                   dotColor: Color = Color.Green,
                   title: String = "Default Title",
                   subText: String = "Default Subtext",
                   startDate: String? = null,
                   endDate: String = "Default End Time"
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController, showUpload = false)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColoruser) // Use BackgroundColor from colors.kt
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Time Schedule",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            ScheduleCard(
                dotColor = Color(0xFFFFA500), // Orange
                title = "Medication",
                subText = "In\n≃ 30min",
                startDate = "Tue, April 10",
                endDate = "Tue, April 24"
            )

            ScheduleCard(
                dotColor = Color(0xFF00C853), // Green
                title = "Doctor's Appointment",
                subText = "Wed, April 8",
                startDate = "Wed, April 8",
                endDate = "5:00PM"
            )

            ScheduleCard(
                dotColor = Color(0xFF00C853), // Green
                title = "Exercise",
                subText = "Daily",
                startDate = "Tue, April 10",
                endDate = "Tue, April 24"
            )
        }
    }
}

@Composable
fun ScheduleCard(
    dotColor: Color,
    title: String,
    subText: String,
    startDate: String? = null,
    endDate: String
) {
    val cardColor = BackgroundColor // Use BackgroundColor from colors.kt
    Row {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(dotColor, shape = CircleShape)
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Top
                ) {
                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(text = subText, fontSize = 12.sp)
                    }

                    Row(
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Delete",
                            tint = Color.Red,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Done",
                            tint = Color.Green,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, start = 120.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Start", fontSize = 10.sp, color = TextColor) // TextColor from colors.kt
                        Text(startDate ?: "--", fontSize = 12.sp)
                    }

                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(40.dp)
                            .background(Color.Gray)
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("End", fontSize = 10.sp, color = TextColor) // TextColor from colors.kt
                        Text(endDate, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
