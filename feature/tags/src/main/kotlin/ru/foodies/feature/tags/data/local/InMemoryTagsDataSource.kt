package ru.foodies.feature.tags.data.local

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.foodies.feature.tags.domain.Tag

class InMemoryTagsDataSource : LocalTagsDataSource {

    private val _filters = MutableStateFlow(emptyList<Tag>())
    override val filters = _filters.asStateFlow()

    override suspend fun apply(tags: List<Tag>) {
        _filters.value = tags
    }

}