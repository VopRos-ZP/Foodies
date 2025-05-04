package ru.vopros.foodies.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vopros.foodies.domain.model.Tag
import ru.vopros.foodies.presentation.R

@Composable
fun FiltersBottomSheet(
    tags: List<Tag>,
    checkedTags: List<Tag>,
    onTagCheckedChange: (List<Tag>) -> Unit,
    onCloseSheet: () -> Unit,
) {
    val checked = remember { mutableStateListOf<Tag>() }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        Text(
            text = stringResource(R.string.pick_up_product),
            style = MaterialTheme.typography.subtitle1,
        )
        Column {
            List(tags.size) { i ->
                val tag = tags[i]
                TextCheckBox(
                    text = tag.name,
                    checked = checkedTags.contains(tag),
                    onCheckedChange = {
                        when (it) {
                            true -> checked.add(tag)
                            else -> checked.remove(tag)
                        }
                    }
                )
            }
        }
        Button(
            onClick = { onCloseSheet() },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White
            ),
        ) {
            Text(
                text = stringResource(R.string.done),
                color = Color.White
            )
        }
    }
}