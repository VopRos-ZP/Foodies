package ru.vopros.foodies.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.vopros.foodies.presentation.R
import ru.vopros.foodies.presentation.theme.Primary

@Composable
fun AddRemoveButtonsRow(
    count: Int,
    color: Color,
    modifier: Modifier = Modifier,
    arrangement: Arrangement.Horizontal = Arrangement.spacedBy(12.dp),
    onAddClick: () -> Unit,
    onRemoveClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onRemoveClick,
            modifier = Modifier.background(color, RoundedCornerShape(8.dp))
        ) {
            Icon(
                painter = painterResource(R.drawable.minus),
                contentDescription = "minus",
                tint = Primary
            )
        }
        Text(
            text = "$count",
            style = MaterialTheme.typography.button
        )
        IconButton(
            modifier = Modifier.background(color, RoundedCornerShape(8.dp)),
            onClick = onAddClick
        ) {
            Icon(
                painter = painterResource(R.drawable.plus),
                contentDescription = "plus",
                tint = Primary
            )
        }
    }
}