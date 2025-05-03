package ru.vopros.foodies.data.repository

import android.content.Context
import kotlinx.serialization.json.Json
import ru.vopros.foodies.data.mapper.CategoryMapper
import ru.vopros.foodies.data.model.CategoryDto
import ru.vopros.foodies.domain.model.Category
import ru.vopros.foodies.domain.repository.RemoteCategoryRepository

class RemoteCategoryRepositoryImpl(
    private val context: Context
) : RemoteCategoryRepository {

    companion object {
        const val FILE_NAME = "Categories.json"
    }

    override suspend fun fetchAll(): List<Category> {
        val content = context.assets
            .open(FILE_NAME)
            .bufferedReader()
            .use { it.readText() }

        return Json.decodeFromString<List<CategoryDto>>(content)
            .map { CategoryMapper.mapDtoToCategory(it) }
    }

}