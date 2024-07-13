package com.arena.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = Orange,
    onPrimary = White,
    primaryContainer = OrangeBg,
    onPrimaryContainer = DarkGrey,
    secondary = SelectedItemColor,
    onSecondary = White,
    secondaryContainer = LightGrey,
    onSecondaryContainer = DarkGrey,
    background = DarkGrey,
    onBackground = White,
    surface = LightGrey,
    onSurface = White
)

private val LightColorPalette = lightColorScheme(
    primary = Orange,
    onPrimary = White,
    primaryContainer = OrangeBg,
    onPrimaryContainer = Black,
    secondary = SelectedItemColor,
    onSecondary = Black,
    secondaryContainer = LightGrey,
    onSecondaryContainer = Black,
    background = WhiteBg,
    onBackground = Black,
    surface = LightGrey,
    onSurface = Black
)

@Composable
fun ArenaTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
