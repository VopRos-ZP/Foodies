package ru.foodies.feature.tags.data

import ru.foodies.feature.tags.data.local.LocalTagsDataSource
import ru.foodies.feature.tags.data.remote.RemoteTagsDataSource
import ru.foodies.feature.tags.data.mapper.toTag
import ru.foodies.feature.tags.domain.Tag
import ru.foodies.feature.tags.domain.TagsRepository

class TagsRepositoryImpl(
    private val localTagsDataSource: LocalTagsDataSource,
    private val remoteTagsDataSource: RemoteTagsDataSource
) : TagsRepository {

    override val filters = localTagsDataSource.filters

    override suspend fun getAll(): List<Tag> {
        return remoteTagsDataSource.getAll().map { it.toTag() }
    }

    override suspend fun applyFilters(tags: List<Tag>) {
        localTagsDataSource.apply(tags)
    }

}