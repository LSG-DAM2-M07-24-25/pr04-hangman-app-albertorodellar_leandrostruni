package com.example.hangmanapp.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import AlphabetViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.example.hangmanapp.viewmodel.GameViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hangmanapp.model.Routes


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GameScreen(
    navController: NavController,
    gameViewModel: GameViewModel,
    alphabetViewModel: AlphabetViewModel
) {

    val difficulty by gameViewModel.difficulty.observeAsState()
    val currentWord by gameViewModel.currentWord.observeAsState()
    val hiddenWord by gameViewModel.hiddenWord.observeAsState()
    val remainingAttempts by gameViewModel.remainingAttempts.observeAsState()
    val gameResult by gameViewModel.gameResult.observeAsState()
    val alphabet by alphabetViewModel.alphabet.collectAsState()
    val currentImage by gameViewModel.currentImage.observeAsState()

    if(gameResult != null){
        LaunchedEffect(Unit) {
            navController.navigate(Routes.ResultScreen.route)
        }
    }


    Box(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //Imagen dinámica que cambia según los intentos
            currentImage?.let { imageRes ->
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Hangman Progress",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
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
                        fontSize = 24.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }


            // Usamos el valor de 'difficulty' para mostrar alguna información relacionada
            Text(
                text = "Dificultad seleccionada: $difficulty",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp)
            )

            // Mostrar intentos restantes
            Text(
                text = "Intentos restantes: $remainingAttempts",
                fontSize = 20.sp,
                color = Color.Red,
                modifier = Modifier.padding(top = 16.dp)
            )

            //Botones con las letras
            LetterButtons(gameViewModel)

        }
    }
}

@Composable
fun LetterButtons(gameViewModel: GameViewModel) {
    val letterState by gameViewModel.letterStates.observeAsState(initial = mapOf())


    Column {
        ('A'..'Z').chunked(5).forEach { row ->
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
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
                                null -> Color.Gray //Letra sin seleccionar
                            },
                            disabledContainerColor = when (state) {
                                true -> Color.Green.copy(alpha = 0.5f) //Mantener verde si es correcta
                                false -> Color.Red.copy(alpha = 0.5f)  //Mantener rojo si es incorrecta
                                else -> Color.Gray.copy(alpha = 0.5f)  //Mantener gris si está deshabilitada sin selección
                            }
                        ),
                        enabled = state == null
                    ) {
                        Text(text = letter.toString())
                    }
                }
            }
        }
    }
}

