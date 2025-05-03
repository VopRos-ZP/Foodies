package ru.vopros.foodies.presentation.screens.catalog

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.Syntax
import ru.vopros.foodies.domain.model.Product
import ru.vopros.foodies.domain.usecase.AddProductToBasketUseCase
import ru.vopros.foodies.domain.usecase.GetBasketListFlowUseCase
import ru.vopros.foodies.domain.usecase.GetBasketSumFlowUseCase
import ru.vopros.foodies.domain.usecase.GetCategoryListFlowUseCase
import ru.vopros.foodies.domain.usecase.GetProductListFlowUseCase
import ru.vopros.foodies.domain.usecase.GetTagListFlowUseCase
import ru.vopros.foodies.domain.usecase.RemoveProductFromBasketUseCase
import ru.vopros.foodies.presentation.orbit.ContainerViewModel

@OptIn(OrbitExperimental::class)
class CatalogViewModel(
    private val getTagListFlowUseCase: GetTagListFlowUseCase,
    private val getCategoryListFlowUseCase: GetCategoryListFlowUseCase,
    private val getProductListFlowUseCase: GetProductListFlowUseCase,
    private val getBasketListFlowUseCase: GetBasketListFlowUseCase,
    private val getBasketSumFlowUseCase: GetBasketSumFlowUseCase,
    private val addProductToBasketUseCase: AddProductToBasketUseCase,
    private val removeProductFromBasketUseCase: RemoveProductFromBasketUseCase
) : ContainerViewModel<CatalogViewState, CatalogSideEffect>(
    initialState = CatalogViewState()
) {

    override suspend fun Syntax<CatalogViewState, CatalogSideEffect>.onCreate() {
        updateState()
    }

    fun onFilterClick() = intent {
        postSideEffect(CatalogSideEffect.OnShowBottomSheet)
    }

    fun onCategoryTabClick(index: Int) = intent {
        postSideEffect(CatalogSideEffect.OnTabClick(index))
    }

    fun onProductClick(product: Product) = intent {
        postSideEffect(CatalogSideEffect.OnProductClick(product.id))
    }

    fun onBasketClick() = intent {
        postSideEffect(CatalogSideEffect.OnBasketClick)
    }

    fun onAddProductToBasket(product: Product) = intent {
        addProductToBasketUseCase(product)
    }

    fun onRemoveProductFromBasket(product: Product) = intent {
        removeProductFromBasketUseCase(product)
    }

    private suspend fun updateState() = subIntent {
        coroutineScope {
            launch { listenTags() }
            launch { listenCategories() }
            launch { listenProducts() }
            launch { listenBaskets() }
            launch { listenBasketSum() }
        }
    }

    private suspend fun listenTags() = listenFlowList(
        flow = getTagListFlowUseCase(),
        copy = { state, it -> state.copy(tags = it) }
    )

    private suspend fun listenCategories() = listenFlowList(
        flow = getCategoryListFlowUseCase(),
        copy = { state, it -> state.copy(categories = it) }
    )

    private suspend fun listenProducts() = listenFlowList(
        flow = getProductListFlowUseCase(),
        copy = { state, it -> state.copy(products = it) }
    )

    private suspend fun listenBaskets() = listenFlowList(
        flow = getBasketListFlowUseCase(),
        copy = { state, it -> state.copy(baskets = it) }
    )

    private suspend fun listenBasketSum() = listenFlowList(
        flow = getBasketSumFlowUseCase(),
        copy = { state, it -> state.copy(sum = it) }
    )

    private suspend fun <T> listenFlowList(
        flow: Flow<T>,
        copy: (CatalogViewState, T) -> CatalogViewState
    ) = subIntent {
        repeatOnSubscription {
            flow.collect { reduce { copy(state, it) } }
        }
    }

}