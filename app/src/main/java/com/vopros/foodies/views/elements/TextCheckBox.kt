package com.vopros.foodies.views.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vopros.foodies.ui.theme.Primary
import com.vopros.foodies.ui.theme.Typography

@Composable
fun TextCheckBox(
    checked: MutableState<Boolean>,
    text: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, style = Typography.body1)
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checked.value = it },
            colors = CheckboxDefaults.colors(
                checkedColor = Primary
            )
        )
    }
}