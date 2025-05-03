package ru.vopros.foodies.domain.repository

import ru.vopros.foodies.domain.model.Tag

interface RemoteTagRepository {
    fun fetchAll(): List<Tag>
}