package ru.vopros.foodies.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("id")
    val id: Int,
    @SerialName("category_id")
    val categoryId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("image")
    val image: String,
    @SerialName("price_current")
    val priceCurrent: Int,
    @SerialName("price_old")
    val priceOld: Int?,
    @SerialName("measure")
    val measure: Int,
    @SerialName("measure_unit")
    val measureUnit: String,
    @SerialName("energy_per_100_grams")
    val energy: Double,
    @SerialName("proteins_per_100_grams")
    val proteins: Double,
    @SerialName("fats_per_100_grams")
    val fats: Double,
    @SerialName("carbohydrates_per_100_grams")
    val carbohydrates: Double,
    @SerialName("tag_ids")
    val tagIds: List<Int>
)
