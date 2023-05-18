package com.vopros.foodies.domain.product

import com.vopros.foodies.domain.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    var id: Int,
    @SerialName("category_id")
    var categoryId: Int,
    var name: String,
    var description: String,
    var image: String,
    @SerialName("price_current")
    var priceCurrent: Int,
    @SerialName("price_old")
    var priceOld: Int?,
    var measure: Int,
    @SerialName("measure_unit")
    var measureUnit: String,
    @SerialName("energy_per_100_grams")
    var energy: Double,
    @SerialName("proteins_per_100_grams")
    var proteins: Double,
    @SerialName("fats_per_100_grams")
    var fats: Double,
    @SerialName("carbohydrates_per_100_grams")
    var carbohydrates: Double,
    @SerialName("tag_ids")
    var tagIds: List<Int>
): Entity() {
    override var entityId: Int
        get() = id
        set(value) { id = value }
}
