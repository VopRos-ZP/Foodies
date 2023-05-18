package com.vopros.foodies.data

import android.content.Context
import com.vopros.foodies.domain.Entity
import com.vopros.foodies.domain.Repository
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

open class RepositoryImpl<T : Entity>(
    private val context: Context,
    private val path: String,
    private val serializer: KSerializer<T>
) : Repository<T> {

    private fun getJsonData(): String {
        return try {
            context.assets.open(path)
                .bufferedReader()
                .use { it.readText() }
        } catch (_: Exception) {
            ""
        }
    }

    override fun fetchAll(): List<T> {
        return Json.decodeFromString(ListSerializer(serializer), getJsonData())
    }

    override fun fetchById(id: Int): T? {
        return fetchAll().find { it.entityId == id }
    }
}