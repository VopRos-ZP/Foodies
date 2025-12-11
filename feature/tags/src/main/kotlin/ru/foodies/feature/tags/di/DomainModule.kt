package ru.foodies.feature.tags.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.foodies.feature.tags.domain.ApplyFiltersUseCase
import ru.foodies.feature.tags.domain.GetTagListUseCase

internal val domainModule = module {
    singleOf(::GetTagListUseCase)
    singleOf(::ApplyFiltersUseCase)
}