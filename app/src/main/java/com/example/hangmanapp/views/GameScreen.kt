package com.example.hangmanapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.hangmanapp.model.Routes




@Composable
fun GameScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Red)
    ) {
        Text(
            text = "Pantalla 3",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable
                { navController.navigate(Routes.LaunchScreen.route) })
    }
}
