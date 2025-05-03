package ru.vopros.foodies.presentation.screens.details

import ru.vopros.foodies.domain.repository.RemoteProductRepository
import ru.vopros.foodies.presentation.orbit.ContainerViewModel

class ProductDetailsViewModel(
    private val remoteProductRepository: RemoteProductRepository
) : ContainerViewModel<ProductDetailsViewState, ProductDetailsSideEffect>(
    initialState = ProductDetailsViewState()
) {

    fun onBackClick() = intent {
        postSideEffect(ProductDetailsSideEffect.OnBackClick)
    }

    fun fetchProductById(id: Int) = intent {
        val product = remoteProductRepository.fetchAll().find { it.id == id }
        reduce { state.copy(product = product) }
    }

}