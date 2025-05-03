package ru.vopros.foodies.presentation.screens.basket

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.Syntax
import ru.vopros.foodies.domain.model.Product
import ru.vopros.foodies.domain.usecase.AddProductToBasketUseCase
import ru.vopros.foodies.domain.usecase.GetBasketListFlowUseCase
import ru.vopros.foodies.domain.usecase.GetBasketSumFlowUseCase
import ru.vopros.foodies.domain.usecase.GetProductListFlowUseCase
import ru.vopros.foodies.domain.usecase.RemoveProductFromBasketUseCase
import ru.vopros.foodies.presentation.orbit.ContainerViewModel

@OptIn(OrbitExperimental::class)
class BasketViewModel(
    private val getProductListFlowUseCase: GetProductListFlowUseCase,
    private val getBasketListFlowUseCase: GetBasketListFlowUseCase,
    private val getBasketSumFlowUseCase: GetBasketSumFlowUseCase,
    private val addProductToBasketUseCase: AddProductToBasketUseCase,
    private val removeProductFromBasketUseCase: RemoveProductFromBasketUseCase,
) : ContainerViewModel<BasketViewState, BasketSideEffect>(
    initialState = BasketViewState()
) {

    override suspend fun Syntax<BasketViewState, BasketSideEffect>.onCreate() {
        coroutineScope {
            launch { listenBasket() }
            launch { listenSum() }
        }
    }

    private suspend fun listenSum() = subIntent {
        repeatOnSubscription {
            getBasketSumFlowUseCase().collect {
                reduce { state.copy(sum = it) }
            }
        }
    }

    private suspend fun listenBasket() = subIntent {
        repeatOnSubscription {
            getProductListFlowUseCase()
                .combine(getBasketListFlowUseCase()) { ps, bs ->
                    bs.map { ps.find { p -> p.id == it.key }!! to it.value }
                }.collect {
                    reduce { state.copy(productsCount = it) }
                }
        }
    }

    fun onBackClick() = intent {
        postSideEffect(BasketSideEffect.OnBackClick)
    }

    fun addProduct(product: Product) = intent {
        addProductToBasketUseCase(product)
    }

    fun removeProduct(product: Product) = intent {
        removeProductFromBasketUseCase(product)
    }

}