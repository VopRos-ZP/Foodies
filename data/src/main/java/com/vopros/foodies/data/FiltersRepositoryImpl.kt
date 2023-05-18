package com.vopros.foodies.data

import com.vopros.foodies.domain.filter.FiltersRepository
import com.vopros.foodies.domain.tag.Tag

class FiltersRepositoryImpl : FiltersRepository {

    private val filters = mutableListOf<Tag>()

    override fun fetchFilters(): MutableList<Tag> {
        return filters
    }

    override fun updateFilters(tags: List<Tag>) {
        filters.clear()
        filters.addAll(tags)
    }
}