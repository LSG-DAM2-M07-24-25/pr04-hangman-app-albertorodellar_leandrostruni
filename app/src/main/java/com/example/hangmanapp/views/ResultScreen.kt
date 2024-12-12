package com.example.hangmanapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
            .padding(16.dp)
    ) {
        Text(
            text = "HANGMAN GAME",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        )



        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ShowGameResults(gameResult ?: "", (attempts?.takeIf { it >= 0 } ?: 0))

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
                    Text(text = "Play Again", style = MaterialTheme.typography.titleMedium)
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
                    Text(text = "Menu", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

/**
 * Muestra los resultados del juego, ya sea que el jugador haya ganado o perdido,
 * junto con los intentos restantes.
 *
 * @param gameResult El resultado del juego ("win" o "lose").
 * @param attempts El número de intentos restantes del jugador. Si es negativo o nulo, se establece en 0.
 */
@Composable
fun ShowGameResults(gameResult: String, attempts: Int) {
    Text(
        text = if (gameResult == "win") "You won!" else "You lost!",
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(16.dp)
    )
    Text(
        text = "Remaining Attempts: $attempts",
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