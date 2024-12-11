package com.example.hangmanapp

import GameViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import com.example.hangmanapp.views.LaunchScreen
import com.example.hangmanapp.views.MenuScreen
import com.example.hangmanapp.views.GameScreen
import com.example.hangmanapp.views.ResultScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gameViewModel: GameViewModel by viewModels<GameViewModel>()
        enableEdgeToEdge()
        setContent {
            HangmanAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HangManGame(
                        modifier = Modifier.padding(innerPadding),
                        gameViewModel = gameViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun HangManGame(modifier: Modifier, gameViewModel: GameViewModel){
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = Routes.LaunchScreen.route
    ) {
        composable(Routes.LaunchScreen.route) { LaunchScreen(navigationController) }
        composable(Routes.MenuScreen.route) { MenuScreen(navigationController, gameViewModel) }
        composable(Routes.GameScreen.route) { GameScreen(navigationController, gameViewModel) }
        composable(Routes.ResultScreen.route) { ResultScreen(navigationController, gameViewModel) }

    }
}


