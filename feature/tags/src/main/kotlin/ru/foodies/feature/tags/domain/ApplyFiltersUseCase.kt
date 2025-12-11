package ru.foodies.feature.tags.domain

class ApplyFiltersUseCase(
    private val tagsRepository: TagsRepository
) {

    suspend operator fun invoke(tags: List<Tag>) = tagsRepository.applyFilters(tags)

}