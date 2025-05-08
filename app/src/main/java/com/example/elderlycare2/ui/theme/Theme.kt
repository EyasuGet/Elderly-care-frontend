// app/src/main/java/com/example/elderlycare2/ui/theme/Theme.kt
package com.example.elderlycare2.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    tertiary = SecondaryColor
)

//// Primary Colors (from your Figma)
//val PrimaryColor = Color(0xFF1D6A6E)
//val PrimaryLightColor = Color(0xFFCAE7E5)
//val PrimaryDarkColor = Color(0xFF114346)
//
//// Secondary Colors
//val SecondaryColor = Color(0xFF6E1D4A)
//val SecondaryLightColor = Color(0xFFE5CADB)
//
//// Text Colors
//val TextColor = PrimaryColor
//val TextSecondaryColor = Color(0xFF666666)
//
//// Background Colors
//val BackgroundColor = Color(0xFFFFFFFF)
//val SurfaceColor = Color(0xFFF5F5F5)
//
//// Button Colors
//val ButtonColor = PrimaryColor
//val ButtonTextColor = Color(0xFFFFFFFF)
//
//// Error Colors
//val ErrorColor = Color(0xFFD32F2F)

@Composable
fun ElderlyCareTheme(
    darkTheme: Boolean = false, // You can implement dark mode later
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme

        else -> {
            lightColorScheme(
                primary = PrimaryColor,
                onPrimary = ButtonTextColor,
                primaryContainer = PrimaryLightColor,
                secondary = SecondaryColor,
                onSecondary = ButtonTextColor,
                secondaryContainer = SecondaryLightColor,
                background = BackgroundColor,
                surface = SurfaceColor,
                error = ErrorColor,
                onBackground = TextColor,
                onSurface = TextColor,
            )
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // You'll need to define your typography
        content = content
    )
}