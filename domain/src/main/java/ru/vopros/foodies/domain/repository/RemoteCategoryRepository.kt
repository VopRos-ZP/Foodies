package ru.vopros.foodies.domain.repository

import ru.vopros.foodies.domain.model.Category

interface RemoteCategoryRepository {
    suspend fun fetchAll(): List<Category>
}