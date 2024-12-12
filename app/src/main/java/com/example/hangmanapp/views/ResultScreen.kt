package com.example.hangmanapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text (
                text = "HANGMAN GAME",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(16.dp))
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
                        .padding(top = 32.dp),
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Text(text = "Volver a jugar", style = MaterialTheme.typography.titleMedium)
                }
                Button(
                    onClick = {
                        navController.navigate(Routes.MenuScreen.route)
                        gameViewModel.playAgain(again = false)
                    },
                    modifier = Modifier
                        .width(180.dp)
                        .padding(top = 8.dp),
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Text(text = "Menu", style = MaterialTheme.typography.titleMedium )
                }
            }
        }
    }
}

@Composable
fun ShowGameResults(gameResult: String, attempts: Int) {
    Text(
        text = if (gameResult == "win") "!Has ganado!" else "!Has perdido!",
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(16.dp)
    )
    Text(
        text = "Intentos restantes: $attempts",
        style = MaterialTheme.typography.bodyLarge,
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