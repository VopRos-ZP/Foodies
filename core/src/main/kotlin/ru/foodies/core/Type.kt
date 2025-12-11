package ru.foodies.core

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class Typography(
    val headline18: TextStyle,
    val headline6: TextStyle,
    val headline4: TextStyle,
    val body: TextStyle,
    val body1: TextStyle,
    val body1Medium: TextStyle,
    val body2: TextStyle,
    val button: TextStyle,
    val buttonSmall: TextStyle,
)

internal val Type = Typography(
    headline18 = TextStyle(
        fontSize = 18.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.SemiBold
    ),
    headline6 = TextStyle(
        fontSize = 20.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Medium
    ),
    headline4 = TextStyle(
        fontSize = 34.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.Normal
    ),
    body = TextStyle(
        fontSize = 17.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.Normal
    ),
    body1 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal
    ),
    body1Medium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Medium
    ),
    body2 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal
    ),
    button = TextStyle(
        fontSize = 16.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium
    ),
    buttonSmall = TextStyle(
        fontSize = 14.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal
    ),
)