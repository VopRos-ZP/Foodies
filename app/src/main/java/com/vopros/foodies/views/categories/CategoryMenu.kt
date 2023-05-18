package com.vopros.foodies.views.categories

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.vopros.foodies.animations.TabIndicator
import com.vopros.foodies.domain.category.Category
import com.vopros.foodies.ui.theme.Primary
import com.vopros.foodies.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryMenu(
    showCategories: Boolean,
    pagerState: PagerState,
    categories: List<Category>,
    onClick: (Int) -> Unit
) {
    AnimatedVisibility(showCategories) {
        ScrollableTabRow(
            modifier = Modifier.padding(bottom = 8.dp),
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.White,
            indicator = { TabIndicator(it, pagerState) },
            edgePadding = 16.dp,
            divider = {}
        ) {
            categories.mapIndexed { i, category ->
                val selected = pagerState.currentPage == i
                Tab(
                    modifier = Modifier
                        .background(Color.Transparent, RoundedCornerShape(8.dp))
                        .zIndex(2f),
                    selected = selected,
                    onClick = { onClick(i) },
                    selectedContentColor = Primary,
                    unselectedContentColor = Color.White
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        text = category.name,
                        style = Typography.button,
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