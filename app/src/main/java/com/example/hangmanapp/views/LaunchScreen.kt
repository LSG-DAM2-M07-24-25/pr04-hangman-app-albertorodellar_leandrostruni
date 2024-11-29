package com.example.hangmanapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hangmanapp.R
import com.example.hangmanapp.model.Routes
import kotlinx.coroutines.delay

@Composable
fun Screen1(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(Routes.Pantalla2.route)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.hangman_game),
            contentDescription = "Hangman Game Logo",
            modifier = Modifier.size(200.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Screen1Preview() {
    val mockNavController = rememberNavController()
    Screen1(navController = mockNavController)
}
