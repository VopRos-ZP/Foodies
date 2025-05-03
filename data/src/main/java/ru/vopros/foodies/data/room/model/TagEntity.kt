package ru.vopros.foodies.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
)
