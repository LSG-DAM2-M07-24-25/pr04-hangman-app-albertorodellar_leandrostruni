package com.example.hangmanapp.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import com.example.hangmanapp.viewmodel.GameViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.hangmanapp.model.Routes


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GameScreen(
    navController: NavController,
    gameViewModel: GameViewModel
) {

    val difficulty by gameViewModel.difficulty.observeAsState()
    val hiddenWord by gameViewModel.hiddenWord.observeAsState()
    val remainingAttempts by gameViewModel.remainingAttempts.observeAsState()
    val gameResult by gameViewModel.gameResult.observeAsState()
    val currentImage by gameViewModel.currentImage.observeAsState()

    if (gameResult != null) {
        LaunchedEffect(Unit) {
            navController.navigate(Routes.ResultScreen.route)
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
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
                .fillMaxWidth()
                .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Imagen dinámica que cambia según los intentos
            currentImage?.let { imageRes ->
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Hangman Progress",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }

            //Mostrar palabra oculta
            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                hiddenWord?.forEach() { char ->
                    Text(
                        text = char.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }


            // Usamos el valor de 'difficulty' para mostrar alguna información relacionada
            Text(
                text = "Difficulty: $difficulty",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar intentos restantes
            Text(
                text = "Remaining Attempts: $remainingAttempts",
                style = MaterialTheme.typography.labelLarge,
                color = Color.Red,
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            //Botones con las letras
            LetterButtons(gameViewModel)

        }
    }
}

@Composable
fun LetterButtons(gameViewModel: GameViewModel) {
    val letterState by gameViewModel.letterStates.observeAsState(initial = mapOf())
    val chunkSize = 6 //Botonones por fila


    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ('A'..'Z').chunked(6).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = if (row.size < chunkSize) Arrangement.Center else Arrangement.SpaceEvenly
            ) {
                row.forEach { letter ->
                    val state = letterState[letter]
                    println("Letra: $letter, Estado: $state")
                    Button(
                        onClick = { gameViewModel.onLetterSelected(letter) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = when (state) {
                                true -> Color.Green //Letra Correcta
                                false -> Color.Red //Letra Incorrecta
                                null -> MaterialTheme.colorScheme.surface //Letra sin seleccionar
                            },
                            disabledContainerColor = when (state) {
                                true -> Color.Green.copy(alpha = 0.5f) //Mantener verde si es correcta
                                false -> Color.Red.copy(alpha = 0.5f)  //Mantener rojo si es incorrecta
                                else -> MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
                            }
                        ),
                        shape = RoundedCornerShape(4.dp),
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface),
                        enabled = state == null,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Text(
                            text = letter.toString(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

