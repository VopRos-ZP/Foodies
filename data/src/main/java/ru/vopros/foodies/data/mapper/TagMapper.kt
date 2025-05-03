package ru.vopros.foodies.data.mapper

import ru.vopros.foodies.data.model.TagDto
import ru.vopros.foodies.data.room.model.TagEntity
import ru.vopros.foodies.domain.model.Tag

object TagMapper {

    fun mapFromDto(dto: TagDto): Tag  = Tag(
        id = dto.id,
        name = dto.name
    )

    fun mapFromEntity(entity: TagEntity): Tag = Tag(
        id = entity.id,
        name = entity.name
    )

    fun mapToEntity(tag: Tag): TagEntity = TagEntity(
        id = tag.id,
        name = tag.name
    )

}