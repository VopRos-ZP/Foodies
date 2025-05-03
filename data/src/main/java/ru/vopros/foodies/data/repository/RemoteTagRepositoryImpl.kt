package ru.vopros.foodies.data.repository

import android.content.Context
import kotlinx.serialization.json.Json
import ru.vopros.foodies.data.mapper.TagMapper
import ru.vopros.foodies.data.model.TagDto
import ru.vopros.foodies.domain.model.Tag
import ru.vopros.foodies.domain.repository.RemoteTagRepository

class RemoteTagRepositoryImpl(
    private val context: Context
) : RemoteTagRepository {

    companion object {
        const val FILE_NAME = "Tags.json"
    }

    override fun fetchAll(): List<Tag> {
        val content = context.assets
            .open(FILE_NAME)
            .bufferedReader()
            .use { it.readText() }

        return Json.decodeFromString<List<TagDto>>(content)
            .map { TagMapper.mapFromDto(it) }
    }

}