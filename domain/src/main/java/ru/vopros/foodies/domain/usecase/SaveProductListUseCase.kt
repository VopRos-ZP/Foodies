package ru.vopros.foodies.domain.usecase

import ru.vopros.foodies.domain.repository.LocalProductRepository
import ru.vopros.foodies.domain.repository.RemoteProductRepository

class SaveProductListUseCase(
    private val remoteProductRepository: RemoteProductRepository,
    private val localProductRepository: LocalProductRepository,
) {

    suspend operator fun invoke() {
        remoteProductRepository.fetchAll().forEach {
            localProductRepository.add(it)
        }
    }

}