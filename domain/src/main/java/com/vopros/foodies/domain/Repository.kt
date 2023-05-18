package com.vopros.foodies.domain

interface Repository<T : Entity> {
    fun fetchAll(): List<T>
    fun fetchById(id: Int): T?
}