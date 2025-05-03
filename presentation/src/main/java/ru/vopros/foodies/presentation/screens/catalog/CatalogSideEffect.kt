package ru.vopros.foodies.presentation.screens.catalog

sealed interface CatalogSideEffect {
    data object OnShowBottomSheet : CatalogSideEffect
    data object OnHideBottomSheet : CatalogSideEffect
    data class OnTabClick(val value: Int) : CatalogSideEffect
    data class OnProductClick(val value: Int) : CatalogSideEffect
    data object OnBasketClick : CatalogSideEffect
}