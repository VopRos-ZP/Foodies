package ru.vopros.foodies.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.vopros.foodies.domain.model.Product

interface LocalProductRepository {
    fun listenAll(): Flow<List<Product>>
    suspend fun add(product: Product)
    suspend fun remove(product: Product)
}