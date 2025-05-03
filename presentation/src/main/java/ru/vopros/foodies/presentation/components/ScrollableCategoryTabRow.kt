package ru.vopros.foodies.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import ru.vopros.foodies.domain.model.Category
import ru.vopros.foodies.presentation.theme.Primary

@Composable
fun ScrollableCategoryTabRow(
    selectedTabIndex: Int,
    categories: List<Category>,
    onTabClick: (Int) -> Unit,
) {
    Surface(elevation = 16.dp) {
        ScrollableTabRow(
            modifier = Modifier.padding(bottom = 8.dp),
            selectedTabIndex = selectedTabIndex,
            backgroundColor = Color.White,
            indicator = { TabIndicator(it, selectedTabIndex) },
            edgePadding = 16.dp,
            divider = {}
        ) {
            categories.mapIndexed { i, category ->
                val selected = selectedTabIndex == i
                Tab(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .clip(RoundedCornerShape(8.dp))
                        .zIndex(2f),
                    selected = selected,
                    onClick = { onTabClick(i) },
                    selectedContentColor = Primary,
                    unselectedContentColor = Color.White
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        text = category.name,
                        style = MaterialTheme.typography.button,
                        color = when (selected) {
                            true -> Color.White
                            else -> Color.Black
                        }
                    )
                }
            }
        }
    }
}