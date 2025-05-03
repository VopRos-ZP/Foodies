package ru.vopros.foodies.data.repository

import android.content.Context
import kotlinx.serialization.json.Json
import ru.vopros.foodies.data.mapper.ProductMapper
import ru.vopros.foodies.data.model.ProductDto
import ru.vopros.foodies.domain.model.Product
import ru.vopros.foodies.domain.repository.RemoteProductRepository

class RemoteProductRepositoryImpl(
    private val context: Context
) : RemoteProductRepository {

    companion object {
        const val FILE_NAME = "Products.json"
    }

    override suspend fun fetchAll(): List<Product> {
        val content = context.assets
            .open(FILE_NAME)
            .bufferedReader()
            .use { it.readText() }

        return Json.decodeFromString<List<ProductDto>>(content)
            .map { ProductMapper.mapDtoToProduct(it) }
    }

}