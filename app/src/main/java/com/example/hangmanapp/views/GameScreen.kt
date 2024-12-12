package com.example.hangmanapp.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import AlphabetViewModel
import com.example.hangmanapp.viewmodel.GameViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


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
    val alphabet by alphabetViewModel.alphabet.collectAsState()


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                alphabet.forEach { letter ->
                    Button(
                        onClick = {
                            alphabetViewModel.onLetterClicked(letter)
                            gameViewModel.onLetterSelected(letter.char)
                                  },
                        enabled = !letter.pulsado,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            disabledContainerColor = Color.Red
                        ),
                        modifier = Modifier.size(64.dp)
                    ) {
                        Text(
                            text = letter.char.toString(),
                            color = Color.White,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}


