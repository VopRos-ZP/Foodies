package ru.vopros.foodies.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.vopros.foodies.data.room.converters.ListTypeConverter

@Entity(tableName = "products")
@TypeConverters(ListTypeConverter::class)
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("category_id")
    val categoryId: Int,
    val name: String,
    val description: String,
    val image: String,
    @ColumnInfo("price_current")
    val priceCurrent: Int,
    @ColumnInfo("price_old")
    val priceOld: Int?,
    val measure: Int,
    @ColumnInfo("measure_unit")
    val measureUnit: String,
    val energy: Double,
    val proteins: Double,
    val fats: Double,
    val carbohydrates: Double,
    @ColumnInfo("tag_ids")
    val tagIds: List<Int>,
)
