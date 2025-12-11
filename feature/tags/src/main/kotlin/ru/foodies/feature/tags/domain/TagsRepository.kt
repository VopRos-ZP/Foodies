package ru.foodies.feature.tags.domain

import kotlinx.coroutines.flow.Flow

interface TagsRepository {
    val filters: Flow<List<Tag>>
    suspend fun getAll(): List<Tag>
    suspend fun applyFilters(tags: List<Tag>)
}