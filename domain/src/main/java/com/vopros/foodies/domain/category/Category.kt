package com.vopros.foodies.domain.category

import com.vopros.foodies.domain.Entity
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    var id: Int,
    var name: String
): Entity() {
    override var entityId: Int
        get() = id
        set(value) { id = value }
}
