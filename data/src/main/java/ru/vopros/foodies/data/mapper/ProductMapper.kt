package ru.vopros.foodies.data.mapper

import ru.vopros.foodies.data.model.ProductDto
import ru.vopros.foodies.data.room.model.ProductEntity
import ru.vopros.foodies.data.room.model.ProductWithCountEntity
import ru.vopros.foodies.domain.model.Product
import ru.vopros.foodies.domain.RUB

object ProductMapper {

    fun mapDtoToProduct(dto: ProductDto): Product = Product(
        id = dto.id,
        categoryId = dto.categoryId,
        name = dto.name,
        description = dto.description,
        image = dto.image,
        priceCurrent = "${dto.priceCurrent} $RUB",
        priceOld = dto.priceOld?.let { "$it $RUB" },
        measure = dto.measure,
        measureUnit = dto.measureUnit,
        energy = dto.energy,
        proteins = dto.proteins,
        fats = dto.fats,
        carbohydrates = dto.carbohydrates,
        tagIds = dto.tagIds
    )

    fun mapEntityToProduct(entity: ProductEntity): Product = Product(
        id = entity.id,
        categoryId = entity.categoryId,
        name = entity.name,
        description = entity.description,
        image = entity.image,
        priceCurrent = "${entity.priceCurrent} $RUB",
        priceOld = entity.priceOld?.let { "$it $RUB" },
        measure = entity.measure,
        measureUnit = entity.measureUnit,
        energy = entity.energy,
        proteins = entity.proteins,
        fats = entity.fats,
        carbohydrates = entity.carbohydrates,
        tagIds = entity.tagIds
    )

    fun mapToEntity(product: Product): ProductEntity = ProductEntity(
        id = product.id,
        categoryId = product.categoryId,
        name = product.name,
        description = product.description,
        image = product.image,
        priceCurrent = product.priceCurrent.takeWhile(Char::isDigit).toInt(),
        priceOld = product.priceOld?.takeWhile(Char::isDigit)?.toInt(),
        measure = product.measure,
        measureUnit = product.measureUnit,
        energy = product.energy,
        proteins = product.proteins,
        fats = product.fats,
        carbohydrates = product.carbohydrates,
        tagIds = product.tagIds
    )

}