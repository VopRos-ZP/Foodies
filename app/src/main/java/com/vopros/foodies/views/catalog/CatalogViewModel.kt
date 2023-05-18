package com.vopros.foodies.views.catalog

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vopros.foodies.domain.Repository
import com.vopros.foodies.domain.basket.Basket
import com.vopros.foodies.domain.basket.BasketRepository
import com.vopros.foodies.domain.filter.FiltersRepository
import com.vopros.foodies.domain.product.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val productsRepository: Repository<Product>,
    private val filtersRepository: FiltersRepository,
    private val basketRepository: BasketRepository
) : ViewModel() {

    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
    val products: LiveData<List<Product>> = _products

    private val _product: MutableLiveData<Product> = MutableLiveData()
    val product: LiveData<Product> = _product

    private val _sum: MutableLiveData<Int> = MutableLiveData()
    val sum: LiveData<Int> = _sum

    private val _baskets: MutableLiveData<SnapshotStateList<Basket>> = MutableLiveData()
    val baskets: LiveData<SnapshotStateList<Basket>> = _baskets

    private val _basket: MutableLiveData<Basket?> = MutableLiveData()
    val basket: LiveData<Basket?> = _basket

    fun findProducts(search: String) {
        fetchProductsBy { p -> fetchFilter()(p) && (
                p.name.contains(search, ignoreCase = true) ||
                        p.description.contains(search, ignoreCase = true)
                ) && search.isNotEmpty()
        }
    }

    fun fetchProducts() {
        fetchProductsBy(fetchFilter())
    }

    fun fetchBasketSum() {
        _sum.postValue(basketRepository.getSum())
    }

    private fun fetchFilter(): (Product) -> Boolean {
        val tags = filtersRepository.fetchFilters()
        val tagIds = tags.map { it.id }
        return when (tagIds.isEmpty()) {
            true -> { _ -> true }
            else -> { p -> p.tagIds.containsAll(tagIds) }
        }
    }

    fun fetchProductById(id: Int) {
        _product.postValue(productsRepository.fetchById(id))
    }

    private fun fetchProductsBy(filter: (Product) -> Boolean) {
        val products = productsRepository.fetchAll().filter(filter)
        _products.postValue(products)
    }

    private fun fetchBasket(id: Int) {
        val found = basketRepository
            .fetchAllProducts()
            .find { it.product.id == id }
        _basket.postValue(found)
        fetchBaskets()
    }

    fun fetchBaskets() {
        val baskets = mutableStateListOf<Basket>()
        baskets.addAll(basketRepository.fetchAllProducts())
        _baskets.postValue(baskets)
        fetchBasketSum()
    }

    fun addProduct(product: Product) {
        basketRepository.addProduct(product)
        fetchBasket(product.id)
    }

    fun removeProduct(product: Product) {
        basketRepository.removeProduct(product)
        fetchBasket(product.id)
    }

}