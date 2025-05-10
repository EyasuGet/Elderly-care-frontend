package com.example.elderlycare2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elderlycare2.R

@Composable
fun BottomNavBar(navController: NavHostController, showUpload : Boolean = true) {
    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar(modifier = Modifier.height(70.dp).border(1.dp, Color.Gray),
        containerColor = Color.White,
        contentColor = Color.Black
    )
    {
        NavigationBarItem(

            icon = {
                Image(
                    painter = painterResource(id = R.drawable.file),
                    contentDescription = "Documents",
                    modifier = Modifier.size(35.dp)
                )
            },
            selected = selectedItem == 0,
            onClick = { selectedItem = 1
                navController.navigate("user_home")},

            )

        NavigationBarItem(
            icon = {
                if(showUpload){
                    Image(
                        painter = painterResource(id = R.drawable.upload),
                        contentDescription = "Upload",
                        modifier = Modifier.size(35.dp)
                    )

                }

            },
            selected = selectedItem == 0,
            onClick = { selectedItem = 1
                navController.navigate("visit_details")} ,

            )

        NavigationBarItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier.size(35.dp)
                )
            },
            selected = selectedItem == 0,
            onClick = { selectedItem =0
                navController.navigate("user_profile")},
            )
    }
}