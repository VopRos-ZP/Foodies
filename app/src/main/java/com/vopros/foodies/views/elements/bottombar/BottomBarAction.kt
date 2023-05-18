package com.vopros.foodies.views.elements.bottombar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

class BottomBarAction {

    val isCustom: MutableState<Boolean> = mutableStateOf(false)
    val showState: MutableState<Boolean> = mutableStateOf(false)
    val onClickState: MutableState<() -> Unit> = mutableStateOf({})
    val contentState: MutableState<@Composable RowScope.() -> Unit> = mutableStateOf({})

    fun showBar(
        onClick: () -> Unit,
        content: @Composable RowScope.() -> Unit
    ) {
        onClickState.value = onClick
        contentState.value = content
        isCustom.value = false
        showState.value = true
    }

    fun showCustomBar(content: @Composable RowScope.() -> Unit) {
        contentState.value = content
        showState.value = true
        isCustom.value = true
    }

    fun hide() {
        showState.value = false
    }

}

val localBBA = compositionLocalOf { BottomBarAction() }
