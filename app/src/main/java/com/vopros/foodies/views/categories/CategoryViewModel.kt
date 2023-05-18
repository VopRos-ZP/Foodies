package com.vopros.foodies.views.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vopros.foodies.domain.Repository
import com.vopros.foodies.domain.category.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: Repository<Category>
): ViewModel() {

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData(emptyList())
    val categories: LiveData<List<Category>> = _categories

    fun fetchCategories() {
        _categories.postValue(categoryRepository.fetchAll())
    }

}