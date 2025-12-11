package ru.foodies.feature.tags.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import ru.foodies.core.FoodiesTheme
import ru.foodies.core.components.PrimaryButton
import ru.foodies.feature.tags.R
import ru.foodies.feature.tags.domain.Tag
import ru.foodies.feature.tags.presentation.select.SelectTagsSideEffect
import ru.foodies.feature.tags.presentation.select.SelectTagsViewModel

@Composable
fun SelectTagsModalSheet(
    viewModel: SelectTagsViewModel = koinViewModel(),
    onDismissRequest: () -> Unit
) {
    val state by viewModel.collectAsState()
    viewModel.collectSideEffect {
        when (it) {
            SelectTagsSideEffect.OnDismiss -> onDismissRequest()
        }
    }
    SelectTagsModalSheetContent(
        onDismissRequest = viewModel::onDismiss,
        tags = state.tags,
        selectedTags = state.selectedTags,
        onCheckedChange = viewModel::onTagClick,
        onApplyClick = viewModel::onApplyClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SelectTagsModalSheetContent(
    onDismissRequest: () -> Unit,
    tags: List<Tag>,
    selectedTags: List<Tag>,
    onCheckedChange: (Tag) -> Unit,
    onApplyClick: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = FoodiesTheme.colorScheme.background,
        contentColor = FoodiesTheme.colorScheme.onBackground,
        shape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp
        ),
        dragHandle = {},
        scrimColor = FoodiesTheme.colorScheme.scrim
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp, horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.select_tags_title),
                style = FoodiesTheme.typography.headline6.copy(
                    color = FoodiesTheme.colorScheme.onBackground
                )
            )
            Column {
                tags.forEachIndexed { i, tag ->
                    TagItem(
                        checked = selectedTags.contains(tag),
                        tag = tag,
                        onClick = { onCheckedChange(tag) }
                    )
                    if (i != tags.lastIndex) {
                        HorizontalDivider(color = FoodiesTheme.colorScheme.outline)
                    }
                }
            }
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onApplyClick,
            ) {
                Text(
                    text = stringResource(R.string.select_tags_button),
                    style = FoodiesTheme.typography.button
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectTagsModalSheetPreview() {
    FoodiesTheme {
        SelectTagsModalSheetContent(
            onDismissRequest = {},
            tags = listOf(
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
            ),
            selectedTags = listOf(
                Tag(
                    id = 1,
                    name = "Новинка",
                ),
            ),
            onCheckedChange = {},
            onApplyClick = {}
        )
    }
}