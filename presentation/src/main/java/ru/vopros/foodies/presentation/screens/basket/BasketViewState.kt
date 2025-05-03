package ru.vopros.foodies.presentation.screens.basket

import ru.vopros.foodies.domain.model.Product

data class BasketViewState(
    val products: List<Product> = emptyList(),
    val productsCount: List<Pair<Product, Int>> = emptyList(),
    val sum: Int = 0,
)