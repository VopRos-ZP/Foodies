package ru.vopros.foodies.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import ru.vopros.foodies.data.room.AppDatabase
import ru.vopros.foodies.data.room.model.BasketEntity
import ru.vopros.foodies.domain.model.Product
import ru.vopros.foodies.domain.repository.BasketRepository

class BasketRepositoryImpl(
    private val appDatabase: AppDatabase
) : BasketRepository {

    override fun listenAll(): Flow<Map<Int, Int>> = appDatabase.basketDao
        .listenAll()
        .map { it.associate { e -> e.productId to e.count } }

    override fun listenSum(): Flow<Int> = appDatabase.basketDao.listenSum()

    override suspend fun addProduct(product: Product) {
        appDatabase.basketDao.insert(BasketEntity(productId = product.id))
    }

    override suspend fun removeProduct(product: Product) {
        appDatabase.basketDao.delete(product.id)
    }

}