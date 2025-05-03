package ru.vopros.foodies.domain.usecase

import ru.vopros.foodies.domain.model.Product
import ru.vopros.foodies.domain.repository.BasketRepository

class RemoveProductFromBasketUseCase(
    private val basketRepository: BasketRepository
) {

    suspend operator fun invoke(product: Product) {
        basketRepository.removeProduct(product)
    }

}