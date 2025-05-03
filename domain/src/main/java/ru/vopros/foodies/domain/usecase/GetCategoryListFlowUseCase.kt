package ru.vopros.foodies.domain.usecase

import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import ru.vopros.foodies.domain.repository.LocalCategoryRepository
import ru.vopros.foodies.domain.repository.RemoteCategoryRepository

class GetCategoryListFlowUseCase(
    private val remoteCategoryRepository: RemoteCategoryRepository,
    private val localCategoryRepository: LocalCategoryRepository
) {

    operator fun invoke() = flow {
        try {
            emit(remoteCategoryRepository.fetchAll())
        } catch (_: Exception) {
            emitAll(localCategoryRepository.listenAll())
        }
    }

}