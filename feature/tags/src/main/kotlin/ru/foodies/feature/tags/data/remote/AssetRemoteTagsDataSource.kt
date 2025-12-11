package ru.foodies.feature.tags.data.remote

import android.content.Context
import kotlinx.serialization.json.Json
import ru.foodies.feature.tags.utils.Assets

class AssetRemoteTagsDataSource(
    private val context: Context
) : RemoteTagsDataSource {

    override suspend fun getAll(): List<TagDto> {
        val json = context.assets
            .open(Assets.FILENAME)
            .readAllBytes()
            .decodeToString()
        return Json.decodeFromString(json)
    }

}