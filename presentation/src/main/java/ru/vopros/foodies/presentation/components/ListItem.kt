package ru.vopros.foodies.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ru.vopros.foodies.presentation.theme.Grey

@Composable
fun ListItem(
    text: String,
    trailingText: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 13.dp)
    ) {
        Text(buildAnnotatedString {
            withStyle(SpanStyle(color = Grey)) {
                append(text)
            }
        })
        Text(buildAnnotatedString {
            append(trailingText)
        })
    }
}