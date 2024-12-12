package com.example.hangmanapp.views

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hangmanapp.viewmodel.GameViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.getDrawable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.hangmanapp.model.Routes
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import com.example.hangmanapp.R

@Composable
fun ResultScreen(navController: NavController, gameViewModel: GameViewModel) {
    val gameResult by gameViewModel.gameResult.observeAsState()
    val attempts by gameViewModel.remainingAttempts.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 64.dp)
    ) {
        Text(
            text = "HANGMAN GAME",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 32.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GifImage(gameResult?: "")
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
                        .wrapContentWidth()
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
                        .wrapContentWidth()
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
 * Muestra una imagen animada (GIF) basada en el resultado del juego.
 *
 * Esta función muestra un GIF de acuerdo con el resultado del juego. Si el jugador ha ganado ("win"),
 * se muestra una animación feliz. Si el jugador ha perdido, se muestra una animación de tristeza (crying).
 *
 * @param gameResult El resultado del juego. Es un `String` que puede ser "win" o "lose", determinando
 *                   qué GIF se debe mostrar.
 */
@Composable
fun GifImage(gameResult: String) {
    val drawableRes = if (gameResult == "win") R.drawable.happy else R.drawable.crying

    Image(
        painter = rememberDrawablePainter(
            drawable = getDrawable(
                LocalContext.current,
                drawableRes
            )
        ),
        contentDescription = if (gameResult == "win") "Happy animation" else "Crying animation",
        contentScale = ContentScale.FillWidth
    )
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