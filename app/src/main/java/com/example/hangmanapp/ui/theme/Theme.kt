package com.example.hangmanapp.ui.theme

import PixelTypography
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


val LightColorScheme = lightColorScheme(
    primary = Color(0xFF8B4513), // Madera oscura
    secondary = Color(0xFF228B22), // Verde cactus
    background = Color(0xFF87CEEB), // Cielo claro
    surface = Color(0xFFF4A460), // Arena
    onPrimary = Color(0xFFFFFFFF), // Blanco
    onSecondary = Color(0xFF000000), // Negro
    onBackground = Color(0xFF654321), // Sombra marrón
    onSurface = Color(0xFF000000) // Negro
)

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF654321), // Sombra marrón
    secondary = Color(0xFF228B22), // Verde cactus
    background = Color(0xFF000000), // Negro
    surface = Color(0xFF8B4513), // Madera oscura
    onPrimary = Color(0xFFFFFFFF), // Blanco
    onSecondary = Color(0xFFF4A460), // Arena
    onBackground = Color(0xFF87CEEB), // Cielo claro
    onSurface = Color(0xFFFFFFFF) // Blanco
)

/*private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40*/

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    )
    */


@Composable
fun HangmanAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = PixelTypography,
        content = content
    )
}