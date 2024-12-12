package com.example.hangmanapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hangmanapp.viewmodel.GameViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.hangmanapp.model.Routes

@Composable
fun ResultScreen(navController: NavController, gameViewModel: GameViewModel) {
    val gameResult by gameViewModel.gameResult.observeAsState()
    val attempts by gameViewModel.remainingAttempts.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowGameResults(gameResult ?: "", attempts ?: 0)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        navController.navigate(Routes.GameScreen.route)
                        gameViewModel.playAgain(again = true)
                    },
                    modifier = Modifier
                        .width(180.dp)
                        .padding(top = 32.dp)
                ) {
                    Text(text = "Volver a jugar", fontSize = 16.sp)
                }
                Button(
                    onClick = {
                        navController.navigate(Routes.MenuScreen.route)
                        gameViewModel.playAgain(again = false)
                    },
                    modifier = Modifier
                        .width(180.dp)
                        .padding(top = 8.dp)
                ) {
                    Text(text = "Menu", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun ShowGameResults(gameResult: String, attempts: Int) {
    Text(
        text = if (gameResult == "win") "!Has ganado!" else "!Has perdido!",
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(16.dp)
    )
    Text(
        text = "Intentos restantes: $attempts",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(16.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResultScreenPreview() {
    val mockNavController = rememberNavController()
    val gameViewModel: GameViewModel = viewModel()

    ResultScreen(navController = mockNavController, gameViewModel = gameViewModel)
}