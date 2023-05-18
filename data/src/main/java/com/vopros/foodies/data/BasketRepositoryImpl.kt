package com.vopros.foodies.data

import com.vopros.foodies.domain.basket.Basket
import com.vopros.foodies.domain.basket.BasketRepository
import com.vopros.foodies.domain.product.Product

class BasketRepositoryImpl : BasketRepository {

    private val products: MutableList<Basket> = mutableListOf()

    override fun getSum(): Int {
        /* price * count */
        return products.sumOf { it.product.priceCurrent * it.count }
    }

    override fun fetchAllProducts(): List<Basket> {
        return products
    }

    override fun addProduct(product: Product) {
        when (val index = products.map { it.product.id }.indexOf(product.id)) {
            (-1) -> products.add(Basket(product, 1))
            else -> {
                val old = products[index].count
                products[index] = Basket(product, old + 1)
            }
        }
    }

    override fun removeProduct(product: Product) {
        val basket = products.find { it.product == product }!!
        val index = products.indexOf(basket)
        when (val count = basket.count) {
            1 -> products.remove(basket)
            else -> products[index] = Basket(product, count - 1)
        }
    }

}