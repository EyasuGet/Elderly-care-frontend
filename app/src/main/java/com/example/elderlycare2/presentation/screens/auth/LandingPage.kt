package com.example.elderlycare2.presentation.screens.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import com.example.elderlycare2.R

@Composable
fun LandingPage() {
    val boxColor = Color(0xFFCAE7E5)
    val textColor = Color(0xFF1D6A6E)

    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {


            // Logo and Title
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(210.dp)
                        .padding(top = 15.dp)
                )

                Text(
                    text = "Nurses by your side, anytime.",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor.copy(alpha = 0.8f),
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            // Features
            Column(
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    Box(R.drawable.supportive, "Supportive", boxColor, textColor)
                    FeatureBox(R.drawable.reliable, "Reliable", boxColor, textColor)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    FeatureBox(R.drawable.easy, "Easy", boxColor, textColor)
                    FeatureBox(R.drawable.emphatetic, "Empathetic", boxColor, textColor)
                }
            }
            Spacer(modifier = Modifier.height(110.dp))

            // Get Started Button
            Button(
                onClick = { /* TODO: Navigation or action */ },
                colors = ButtonDefaults.buttonColors(containerColor = textColor),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .width(300.dp)
                    .height(70.dp)

            ) {
                Text(text = "Get Started", color = Color.White, fontSize = 20.sp, fontWeight = Bold)
            }
        }
    }
}