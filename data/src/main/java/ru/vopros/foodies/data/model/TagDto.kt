package ru.vopros.foodies.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
)
