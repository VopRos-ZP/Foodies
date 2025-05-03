package ru.vopros.foodies.domain.usecase

import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import ru.vopros.foodies.domain.repository.LocalTagRepository
import ru.vopros.foodies.domain.repository.RemoteTagRepository

class GetTagListFlowUseCase(
    private val remoteTagRepository: RemoteTagRepository,
    private val localTagRepository: LocalTagRepository
) {

    operator fun invoke() = flow {
        try {
            emit(remoteTagRepository.fetchAll())
        } catch (_: Exception) {
            emitAll(localTagRepository.listenAll())
        }
    }

}