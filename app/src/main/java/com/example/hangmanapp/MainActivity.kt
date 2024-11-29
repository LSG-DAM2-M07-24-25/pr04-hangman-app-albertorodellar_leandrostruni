package com.example.hangmanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hangmanapp.model.Routes
import com.example.hangmanapp.ui.theme.HangmanAppTheme
import com.example.hangmanapp.views.Screen1
import com.example.hangmanapp.views.Screen2
import com.example.hangmanapp.views.Screen3

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HangmanAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HangManGame(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HangManGame(modifier: Modifier){
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = Routes.Pantalla1.route
    ) {
        composable(Routes.Pantalla1.route) { Screen1(navigationController) }
        composable(Routes.Pantalla2.route) { Screen2(navigationController) }
        composable(Routes.Pantalla3.route) { Screen3(navigationController) }
    }
}
