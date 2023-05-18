package com.vopros.foodies.views.filters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vopros.foodies.domain.filter.FiltersRepository
import com.vopros.foodies.domain.Repository
import com.vopros.foodies.domain.tag.Tag
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor(
    private val tagsRepository: Repository<Tag>,
    private val filtersRepository: FiltersRepository
): ViewModel() {

    private val _tags: MutableLiveData<List<Tag>> = MutableLiveData(emptyList())
    val tags: LiveData<List<Tag>> = _tags

    private val _filter: MutableLiveData<MutableList<Tag>> = MutableLiveData(mutableListOf())
    val filter: LiveData<MutableList<Tag>> = _filter

    fun fetchTags() {
        _tags.postValue(tagsRepository.fetchAll())
    }

    fun fetchFilters() {
        _filter.postValue(filtersRepository.fetchFilters())
    }

    fun applyFilters(tags: MutableList<Tag>) {
        filtersRepository.updateFilters(tags)
        fetchFilters()
    }

}