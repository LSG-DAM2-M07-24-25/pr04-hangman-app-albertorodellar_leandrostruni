package com.example.hangmanapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hangmanapp.R
import com.example.hangmanapp.model.Routes


@Composable
fun Screen3(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Game Screen",
                modifier = Modifier
                    .clickable
                    { navController.navigate(Routes.Pantalla1.route) })
            Image(
                painter = painterResource(id = R.drawable.hangman_game),
                contentDescription = "Hangman Game Logo",
                modifier = Modifier.size(250.dp)
            )


            LetterButton()


        }

    }
}

@Composable
fun LetterButton() {
    Button(
        onClick = { /*TODO*/ }
    ) {
        Text(text = "A")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Screen3Preview() {
    val mockNavController = rememberNavController()
    Screen3(navController = mockNavController)
}
