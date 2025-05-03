package ru.vopros.foodies.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import ru.vopros.foodies.presentation.theme.Primary

@Composable
fun TabIndicator(
    tabPositions: List<TabPosition>,
    selectedTabIndex: Int,
) {
    val indicatorStart = tabPositions[selectedTabIndex].left
    val indicatorEnd = tabPositions[selectedTabIndex].right
    Box(
        Modifier
            .offset { IntOffset(x = indicatorStart.roundToPx(), y = 0) }
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .fillMaxSize()
            .background(Primary, RoundedCornerShape(size = 8.dp))
            .zIndex(1f)
    )
}