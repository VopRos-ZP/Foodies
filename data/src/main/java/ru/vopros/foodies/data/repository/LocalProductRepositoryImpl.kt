package ru.vopros.foodies.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vopros.foodies.data.mapper.ProductMapper
import ru.vopros.foodies.data.room.AppDatabase
import ru.vopros.foodies.domain.model.Product
import ru.vopros.foodies.domain.repository.LocalProductRepository

class LocalProductRepositoryImpl(
    private val appDatabase: AppDatabase
) : LocalProductRepository {

    override fun listenAll(): Flow<List<Product>> = appDatabase.productDao
        .listenAll()
        .map { it.map(ProductMapper::mapEntityToProduct) }

    override suspend fun add(product: Product) {
        appDatabase.productDao.insert(ProductMapper.mapToEntity(product))
    }

    override suspend fun remove(product: Product) {
        appDatabase.productDao.delete(ProductMapper.mapToEntity(product))
    }

}