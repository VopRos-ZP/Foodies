package ru.foodies.feature.tags.data

import ru.foodies.feature.tags.data.local.LocalTagsDataSource
import ru.foodies.feature.tags.data.mapper.toTag
import ru.foodies.feature.tags.domain.Tag
import ru.foodies.feature.tags.domain.TagsRepository

class TagsRepositoryImpl(
    private val localTagsDataSource: LocalTagsDataSource
) : TagsRepository {

    override suspend fun getAll(): List<Tag> {
        return localTagsDataSource.getAll().map { it.toTag() }
    }

}