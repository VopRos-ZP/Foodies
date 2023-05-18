package com.vopros.foodies.views.elements.topbar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

class TopBarAction {

    val isFiltersApply: MutableState<Boolean> = mutableStateOf(false)
    val toplineState: MutableState<ToplineState> = mutableStateOf(ToplineState.DEFAULT)

    fun updateToplineState(newState: ToplineState) {
        toplineState.value = newState
    }

    fun applyFilters() {
        isFiltersApply.value = !isFiltersApply.value
    }

}

val localTBA = compositionLocalOf { TopBarAction() }