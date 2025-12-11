package ru.foodies.feature.tags.data.local

import kotlinx.coroutines.flow.Flow
import ru.foodies.feature.tags.domain.Tag

interface LocalTagsDataSource {
    val filters: Flow<List<Tag>>
    suspend fun apply(tags: List<Tag>)
}