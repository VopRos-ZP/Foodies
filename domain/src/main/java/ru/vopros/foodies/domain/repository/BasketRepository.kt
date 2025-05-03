package ru.vopros.foodies.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.vopros.foodies.domain.model.Product

interface BasketRepository {
    fun listenAll() : Flow<Map<Int, Int>>
    fun listenSum() : Flow<Int>
    suspend fun addProduct(product: Product)
    suspend fun removeProduct(product: Product)
}