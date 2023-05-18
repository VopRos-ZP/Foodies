package com.vopros.foodies.domain.filter

import com.vopros.foodies.domain.tag.Tag

interface FiltersRepository {
    fun fetchFilters(): MutableList<Tag>
    fun updateFilters(tags: List<Tag>)
}