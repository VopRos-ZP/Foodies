package ru.foodies.feature.tags.data.local

import android.content.Context
import kotlinx.serialization.json.Json
import ru.foodies.feature.tags.utils.Assets

class AssetLocalTagsDataSource(
    private val context: Context
) : LocalTagsDataSource {

    override suspend fun getAll(): List<TagDto> {
        val json = context.assets
            .open(Assets.FILENAME)
            .readAllBytes()
            .decodeToString()
        return Json.decodeFromString(json)
    }

}