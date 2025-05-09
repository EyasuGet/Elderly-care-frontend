package com.example.elderlycare2.presentation.screens.auth.landingPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.elderlycare2.R
import com.example.elderlycare2.ui.theme.ElderlyCareTheme

val PrimaryColor = Color(0xFF1D6A6E)
val BackgroundBoxColor = Color(0xFFCAE7E5)
val TextColor = PrimaryColor

@Composable
fun LandingPage(onGetStarted: () -> Unit) {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(210.dp)
                        .padding(top = 15.dp)
                )

                Text(
                    text = "Nurses by your side, anytime.",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextColor.copy(alpha = 0.8f),
                    modifier = Modifier.padding(top = 10.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    FeatureBox(R.drawable.supportive, "Supportive")
                    FeatureBox(R.drawable.reliable, "Reliable")
                }
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    FeatureBox(R.drawable.easy, "Easy")
                    FeatureBox(R.drawable.emphathetic, "Empathetic")
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = onGetStarted,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .width(300.dp)
                    .height(70.dp)
            ) {
                Text(
                    text = "Get Started",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun FeatureBox(imageResId: Int, label: String) {
    Column(
        modifier = Modifier
            .border(width = 1.dp, color = PrimaryColor, shape = RoundedCornerShape(12.dp))
            .background(BackgroundBoxColor, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
            .size(120.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = label,
            modifier = Modifier.size(95.dp)
        )
        Text(
            text = label,
            color = TextColor,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}


