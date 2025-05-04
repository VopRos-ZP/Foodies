package ru.vopros.foodies.presentation.screens.catalog

import ru.vopros.foodies.domain.model.Category
import ru.vopros.foodies.domain.model.Product
import ru.vopros.foodies.domain.model.Tag

data class CatalogViewState(
    val tags: List<Tag> = emptyList(),
    val checkedTags: List<Tag> = emptyList(),
    val categories: List<Category> = emptyList(),
    val products: List<Product> = emptyList(),
    val baskets: Map<Int, Int> = emptyMap(),
    val sum: Int = 0
)
