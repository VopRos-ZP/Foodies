package ru.foodies.feature.tags.data.mapper

import ru.foodies.feature.tags.data.local.TagDto
import ru.foodies.feature.tags.domain.Tag

fun TagDto.toTag(): Tag = Tag(
    id = id,
    name = name
)