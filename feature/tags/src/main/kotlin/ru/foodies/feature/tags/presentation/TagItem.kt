package ru.foodies.feature.tags.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import ru.foodies.core.FoodiesTheme
import ru.foodies.feature.tags.domain.Tag

@Composable
fun TagItem(
    modifier: Modifier = Modifier,
    checked: Boolean,
    tag: Tag,
    onClick: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(FoodiesTheme.colorScheme.background)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = tag.name,
            style = FoodiesTheme.typography.body1.copy(
                color = FoodiesTheme.colorScheme.onBackground
            ),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Checkbox(
            checked = checked,
            onCheckedChange = onClick,
            colors = CheckboxDefaults.colors(
                checkedColor = FoodiesTheme.colorScheme.primary,
                uncheckedColor = FoodiesTheme.colorScheme.outline,
            )
        )
    }
}

class TagsParameterProvider : PreviewParameterProvider<Tag> {

    override val values = sequenceOf(
        Tag(
            id = 1,
            name = "Новинка",
        ),
        Tag(
            id = 2,
            name = "Вегетарианское блюдо",
        ),
        Tag(
            id = 3,
            name = "Хит!",
        ),
    )
}

@Preview
@Composable
private fun TagItemPreview(
    @PreviewParameter(TagsParameterProvider::class) tag: Tag
) {
    FoodiesTheme {
        TagItem(
            checked = tag.id % 2 == 0,
            tag = tag,
            onClick = {},
        )
    }
}