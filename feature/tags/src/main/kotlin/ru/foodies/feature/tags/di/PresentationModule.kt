package ru.foodies.feature.tags.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.foodies.feature.tags.presentation.select.SelectTagsViewModel

internal val presentationModule = module {
    viewModelOf(::SelectTagsViewModel)
}