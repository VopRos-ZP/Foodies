package ru.foodies.feature.tags.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.foodies.feature.tags.data.TagsRepositoryImpl
import ru.foodies.feature.tags.data.local.AssetLocalTagsDataSource
import ru.foodies.feature.tags.data.local.LocalTagsDataSource
import ru.foodies.feature.tags.domain.TagsRepository

internal val dataModule = module {
    singleOf(::AssetLocalTagsDataSource) { bind<LocalTagsDataSource>() }
    singleOf(::TagsRepositoryImpl) { bind<TagsRepository>() }
}