package ru.vopros.foodies.presentation.screens.details

sealed interface ProductDetailsSideEffect {
    data object OnBackClick : ProductDetailsSideEffect
}