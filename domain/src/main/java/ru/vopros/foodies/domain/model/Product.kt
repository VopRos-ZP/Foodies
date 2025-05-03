package ru.vopros.foodies.domain.model

data class Product(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val description: String,
    val image: String,
    val priceCurrent: String,
    val priceOld: String?,
    val measure: Int,
    val measureUnit: String,
    val energy: Double,
    val proteins: Double,
    val fats: Double,
    val carbohydrates: Double,
    val tagIds: List<Int>
)
