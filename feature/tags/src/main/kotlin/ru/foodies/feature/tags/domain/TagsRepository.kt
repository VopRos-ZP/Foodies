package ru.foodies.feature.tags.domain

import ru.foodies.feature.tags.domain.Tag

interface TagsRepository {
    suspend fun getAll(): List<Tag>
}