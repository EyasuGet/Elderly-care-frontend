package com.example.elderlycare2.presentation.screens.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.elderlycare2.R
import com.example.elderlycare2.presentation.state.UserEditProfileUiEvent
import com.example.elderlycare2.presentation.viewmodel.UserProfileViewModel
import com.example.elderlycare2.ui.components.BottomNavBar
import com.example.elderlycare2.ui.theme.BackgroundColor
import com.example.elderlycare2.ui.theme.BackgroundColor1
import com.example.elderlycare2.ui.theme.BackgroundColoruser
import com.example.elderlycare2.ui.theme.BorderFocusedColor
import com.example.elderlycare2.ui.theme.BorderUnfocusedColor
import com.example.elderlycare2.ui.theme.ButtonTextColor

@Composable
fun UserProfileScreen(
    navController: NavHostController,
    viewModel: UserProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    // Load profile on first composition
    LaunchedEffect(Unit) {
        viewModel.onEvent(UserEditProfileUiEvent.LoadProfile)
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController, showUpload = false)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .background(BackgroundColoruser)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Profile",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(50.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.width(35.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Text(state.fullName, fontSize = 32.sp, fontWeight = FontWeight.SemiBold)
                    Text("Email: ${state.email}", fontSize = 14.sp, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ProfileField(
                label = "Full Name",
                value = state.fullName,
                onValueChange = { viewModel.onEvent(UserEditProfileUiEvent.UpdateFullName(it)) }
            )
            ProfileField(
                label = "Gender",
                value = state.gender,
                onValueChange = { viewModel.onEvent(UserEditProfileUiEvent.UpdateGender(it)) }
            )
            ProfileField(
                label = "Phone Number",
                value = state.phoneNumber,
                onValueChange = { viewModel.onEvent(UserEditProfileUiEvent.UpdatePhoneNumber(it)) }
            )
            ProfileField(
                label = "Care Taker",
                value = state.caretaker,
                onValueChange = { viewModel.onEvent(UserEditProfileUiEvent.UpdateCaretaker(it)) }
            )
            ProfileField(
                label = "Address",
                value = state.address,
                onValueChange = { viewModel.onEvent(UserEditProfileUiEvent.UpdateAddress(it)) }
            )
            ProfileField(
                label = "Email",
                value = state.email,
                onValueChange = { viewModel.onEvent(UserEditProfileUiEvent.UpdateEmail(it)) }
            )

            Spacer(modifier = Modifier.height(30.dp))

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            if (state.error != null) {
                Text(
                    text = state.error ?: "",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            if (state.isSuccess) {
                Text(
                    text = "Profile updated!",
                    color = Color.Green,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Button(
                onClick = { viewModel.onEvent(UserEditProfileUiEvent.SubmitProfile) },
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(containerColor = BackgroundColor1)
            ) {
                Text("Save", color = ButtonTextColor)
            }
        }
    }
}

@Composable
fun ProfileField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    val TextfieldBackground = BackgroundColor
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(top = 10.dp).width(130.dp)) {
            Text(label, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Column {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = TextfieldBackground,
                    unfocusedContainerColor = TextfieldBackground,
                    focusedBorderColor = BorderFocusedColor,
                    unfocusedBorderColor = BorderUnfocusedColor
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}