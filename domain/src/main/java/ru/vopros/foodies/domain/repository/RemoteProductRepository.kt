package ru.vopros.foodies.domain.repository

import ru.vopros.foodies.domain.model.Product

interface RemoteProductRepository {
    suspend fun fetchAll(): List<Product>
}