package ru.foodies.core

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val White = Color(0xFFFFFFFF)
val Black50 = Color(0x80000000)
val Black87 = Color(0xDE000000)
val Orange = Color(0xFFF15412)
val Grey = Color(0xFFF5F5F5)
val Black60 = Color(0x99000000)
val Black12 = Color(0x1F000000)

@Immutable
data class FoodiesColors(
    val background: Color,
    val onBackground: Color,
    val primary: Color,
    val onPrimary: Color,
    val surface: Color,
    val onSurface: Color,
    val outline: Color,
    val scrim: Color,
)

internal val lightColors = FoodiesColors(
    background = White,
    onBackground = Black87,
    primary = Orange,
    onPrimary = White,
    surface = Grey,
    onSurface = Black60,
    outline = Black12,
    scrim = Black50
)