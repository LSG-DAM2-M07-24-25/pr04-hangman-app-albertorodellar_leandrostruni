package com.example.hangmanapp.views

import com.example.hangmanapp.viewmodel.GameViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hangmanapp.R
import com.example.hangmanapp.model.Routes


@Composable
fun MenuScreen(navController: NavController, gameViewModel: GameViewModel) {
    var selectedDifficulty by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "HANGMAN",
                fontSize = 52.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = "GAME",
                fontSize = 52.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(id = R.drawable.hagman_logo),
                contentDescription = "Hangman Game Logo",
                modifier = Modifier.size(400.dp)
            )

            Spacer(modifier = Modifier.height(42.dp))

            DifficultyDropdown(
                selectedDifficulty = selectedDifficulty,
                onSelectionChange = { selectedDifficulty = it }
            )



            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        gameViewModel.setDifficulty(selectedDifficulty)
                        navController.navigate(Routes.GameScreen.route)
                    },
                    enabled = selectedDifficulty.isNotEmpty(),
                    modifier = Modifier
                        .width(150.dp)
                        .padding(top = 32.dp)
                ) {
                    Text(text = "Jugar")
                }
                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier
                        .width(150.dp)
                        .padding(top = 8.dp)
                ) {
                    Text(text = "Ayuda")
                }
                // Si pulsa el boton muestra el dialog
                if (showDialog) {
                    HelpDialog(onDismiss = { showDialog = false })
                }
            }
        }
    }
}

@Composable
fun HelpDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { onDismiss() }
                    ) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Cerrar"
                        )
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Cómo jugar",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                Text(
                    text = "1. El juego consiste en adivinar una palabra secreta antes de que se complete el dibujo del ahorcado.\n" +
                            "2. Se te mostrará la cantidad de letras de la palabra según la dificultad seleccionada en forma de guiones bajos (_).\n" +
                            "3. Deberás adivinar las letras de la palabra, una por una, pulsando en las letras del abecedario.\n" +
                            "4. Si eliges una letra correcta, aparecerá en su lugar en la palabra.\n" +
                            "5. Si eliges una letra incorrecta, se sumará una parte al dibujo del ahorcado y se te restará un intento.\n" +
                            "6. Tienes un número limitado de intentos para adivinar la palabra antes de perder el juego.\n" +
                            "7. Si completas la palabra antes de que se termine el dibujo, ¡ganas!\n" +
                            "8. Si el dibujo del ahorcado se completa antes de que adivines la palabra, pierdes el juego.\n\n" +
                            "¡Sigue intentándolo y diviértete mientras mejoras tu habilidad para adivinar palabras!",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}


@Composable
fun DifficultyDropdown(selectedDifficulty: String, onSelectionChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val difficulty = listOf("Facil", "Medio", "Dificil")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFF1F1F1),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
                .clickable { expanded = !expanded }
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedDifficulty.ifEmpty { "Selecciona la dificultad" },
                color = Color.Black,
                modifier = Modifier.weight(1f),
                fontSize = 18.sp
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Arrow down",
                modifier = Modifier.size(24.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            difficulty.forEach { difficulty ->
                DropdownMenuItem(
                    text = { Text(text = difficulty, fontSize = 18.sp) },
                    onClick = {
                        expanded = false
                        onSelectionChange(difficulty)
                    }
                )

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MenuScreenPreview() {
    val mockNavController = rememberNavController()
    val gameViewModel: GameViewModel = viewModel()

    MenuScreen(navController = mockNavController, gameViewModel = gameViewModel)
}