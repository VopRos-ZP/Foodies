package ru.vopros.foodies.data.room.model

import androidx.room.ColumnInfo

data class ProductWithCountEntity(
    @ColumnInfo("product_id")
    val productId: Int,
    val count: Int,
)
