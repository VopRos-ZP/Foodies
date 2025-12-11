package ru.foodies.feature.tags.data.local

interface LocalTagsDataSource {
    suspend fun getAll(): List<TagDto>
}