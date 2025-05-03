package ru.vopros.foodies.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.vopros.foodies.domain.model.Category

interface LocalCategoryRepository {
    fun listenAll(): Flow<List<Category>>
    suspend fun add(category: Category)
    suspend fun remove(category: Category)
}