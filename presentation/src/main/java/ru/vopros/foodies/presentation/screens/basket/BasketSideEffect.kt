package ru.vopros.foodies.presentation.screens.basket

sealed interface BasketSideEffect {
    data object OnBackClick : BasketSideEffect
}