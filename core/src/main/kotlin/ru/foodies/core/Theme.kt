package ru.foodies.core

import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

val LocalColorScheme = compositionLocalOf { lightColors }
val LocalTypography = compositionLocalOf { Type }

object FoodiesTheme {

    val colorScheme: FoodiesColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

}

@Composable
fun FoodiesTheme(
    content: @Composable () -> Unit,
) {
    val colorScheme = lightColors
    val type = Type
    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides type,
        LocalMinimumInteractiveComponentSize provides 20.dp,
        content = content
    )
}