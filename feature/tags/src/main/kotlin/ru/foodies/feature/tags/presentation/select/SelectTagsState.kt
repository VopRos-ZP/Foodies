package ru.foodies.feature.tags.presentation.select

import ru.foodies.feature.tags.domain.Tag

data class SelectTagsState(
    val tags: List<Tag> = emptyList(),
    val selectedTags: List<Tag> = emptyList(),
)
