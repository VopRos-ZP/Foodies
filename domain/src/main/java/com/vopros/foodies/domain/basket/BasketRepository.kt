package com.vopros.foodies.domain.basket

import com.vopros.foodies.domain.product.Product

/**
 * Корзина с продуктами пользователя
 * */
interface BasketRepository {
    fun getSum(): Int
    fun fetchAllProducts(): List<Basket>
    fun addProduct(product: Product)
    fun removeProduct(product: Product)
}
