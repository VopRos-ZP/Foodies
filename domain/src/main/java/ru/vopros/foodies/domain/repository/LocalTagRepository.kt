package ru.vopros.foodies.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.vopros.foodies.domain.model.Tag

interface LocalTagRepository {
    fun listenAll(): Flow<List<Tag>>
    suspend fun add(tag: Tag)
    suspend fun remove(tag: Tag)
}