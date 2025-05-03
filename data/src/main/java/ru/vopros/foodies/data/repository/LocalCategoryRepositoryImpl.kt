package ru.vopros.foodies.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vopros.foodies.data.mapper.CategoryMapper
import ru.vopros.foodies.data.room.AppDatabase
import ru.vopros.foodies.domain.model.Category
import ru.vopros.foodies.domain.repository.LocalCategoryRepository

class LocalCategoryRepositoryImpl(
    private val appDatabase: AppDatabase,
) : LocalCategoryRepository {

    override fun listenAll(): Flow<List<Category>> = appDatabase.categoryDao
        .listenAll()
        .map { it.map(CategoryMapper::mapFromEntity) }

    override suspend fun add(category: Category) {
        appDatabase.categoryDao.insert(CategoryMapper.mapToEntity(category))
    }

    override suspend fun remove(category: Category) {
        appDatabase.categoryDao.delete(CategoryMapper.mapToEntity(category))
    }
}