package com.vopros.foodies.views.elements.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomButtonBar() {
    val action = localBBA.current
    AnimatedVisibility(action.showState.value) {
        BottomBar(
            isCustom = action.isCustom.value,
            onClick = action.onClickState.value,
            content = action.contentState.value
        )
    }
}

@Composable
fun BottomBar(
    isCustom: Boolean,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    DefaultBottomBar {
        if (isCustom) {
            content(this)
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onClick,
                    content = content
                )
            }
        }
    }
}

@Composable
fun DefaultBottomBar(content: @Composable RowScope.() -> Unit) {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.White,
        elevation = 4.dp,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        content = content
    )
}
