package ru.vopros.foodies.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vopros.foodies.data.mapper.TagMapper
import ru.vopros.foodies.data.room.AppDatabase
import ru.vopros.foodies.domain.model.Tag
import ru.vopros.foodies.domain.repository.LocalTagRepository

class LocalTagRepositoryImpl(
    private val appDatabase: AppDatabase
) : LocalTagRepository {

    override fun listenAll(): Flow<List<Tag>> = appDatabase.tagDao
        .listenAll()
        .map { it.map(TagMapper::mapFromEntity) }

    override suspend fun add(tag: Tag) {
        appDatabase.tagDao.insert(TagMapper.mapToEntity(tag))
    }

    override suspend fun remove(tag: Tag) {
        appDatabase.tagDao.delete(TagMapper.mapToEntity(tag))
    }

}