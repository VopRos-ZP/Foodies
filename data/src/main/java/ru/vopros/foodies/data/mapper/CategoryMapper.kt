package ru.vopros.foodies.data.mapper

import ru.vopros.foodies.data.model.CategoryDto
import ru.vopros.foodies.data.room.model.CategoryEntity
import ru.vopros.foodies.domain.model.Category

object CategoryMapper {

    fun mapDtoToCategory(categoryDto: CategoryDto): Category {
        return Category(
            id = categoryDto.id,
            name = categoryDto.name
        )
    }

    fun mapFromEntity(entity: CategoryEntity): Category {
        return Category(
            id = entity.id,
            name = entity.name
        )
    }

    fun mapToEntity(category: Category): CategoryEntity {
        return CategoryEntity(
            id = category.id,
            name = category.name
        )
    }

}