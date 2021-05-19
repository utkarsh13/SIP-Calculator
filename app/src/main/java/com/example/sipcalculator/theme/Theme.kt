package com.example.sipcalculator.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Teal800,
    primaryVariant = Teal800Darker,
    secondary = Red800,
    secondaryVariant = Red800Darker,
    background = BgColor,
    surface = BgColor,
    error = Red800,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White,
)

private val LightColorPalette = lightColors(
    primary = Teal800,
    primaryVariant = Teal800Darker,
    secondary = Red800,
    secondaryVariant = Red800Darker,
    background = Color.White,
    surface = BgColor,
    error = Red800,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

@Composable
fun SipCalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography(),
        shapes = Shapes,
        content = content
    )
}