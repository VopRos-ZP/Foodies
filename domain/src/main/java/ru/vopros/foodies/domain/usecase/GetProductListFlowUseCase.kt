package ru.vopros.foodies.domain.usecase

import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import ru.vopros.foodies.domain.repository.LocalProductRepository
import ru.vopros.foodies.domain.repository.RemoteProductRepository

class GetProductListFlowUseCase(
    private val remoteProductRepository: RemoteProductRepository,
    private val localProductRepository: LocalProductRepository,
) {

    operator fun invoke() = flow {
        try {
            emit(remoteProductRepository.fetchAll())
        } catch (_: Exception) {
            emitAll(localProductRepository.listenAll())
        }
    }

}