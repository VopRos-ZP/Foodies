package ru.foodies.feature.tags.domain

class GetTagListUseCase(
    private val tagsRepository: TagsRepository
) {

    suspend operator fun invoke() = tagsRepository.getAll()

}