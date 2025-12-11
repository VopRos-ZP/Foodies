package ru.foodies.feature.tags.presentation.select

sealed interface SelectTagsSideEffect {
    data object OnDismiss : SelectTagsSideEffect
}