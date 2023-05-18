package com.vopros.foodies.domain.basket

import com.vopros.foodies.domain.product.Product

data class Basket(
    var product: Product,
    var count: Int
)
