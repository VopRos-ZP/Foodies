package ru.foodies.feature.tags.data.remote

interface RemoteTagsDataSource {
    suspend fun getAll(): List<TagDto>
}