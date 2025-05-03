package ru.vopros.foodies.domain.usecase

import kotlinx.coroutines.flow.combine

class GetBasketSumFlowUseCase(
    private val getBasketListFlowUseCase: GetBasketListFlowUseCase,
    private val getProductListFlowUseCase: GetProductListFlowUseCase,
) {

    operator fun invoke() = getProductListFlowUseCase()
        .combine(getBasketListFlowUseCase()) { ps, bs ->
            bs.map { ps.find { p -> p.id == it.key }!! to it.value }
                .sumOf { it.first.priceCurrent.takeWhile(Char::isDigit).toInt() * it.second }
        }

}