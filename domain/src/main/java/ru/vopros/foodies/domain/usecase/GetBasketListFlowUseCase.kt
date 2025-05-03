package ru.vopros.foodies.domain.usecase

import ru.vopros.foodies.domain.repository.BasketRepository

class GetBasketListFlowUseCase(
    private val basketRepository: BasketRepository
) {

    operator fun invoke() = basketRepository.listenAll()

}