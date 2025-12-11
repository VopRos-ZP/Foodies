package ru.foodies.feature.tags.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.foodies.feature.tags.data.TagsRepositoryImpl
import ru.foodies.feature.tags.data.local.InMemoryTagsDataSource
import ru.foodies.feature.tags.data.local.LocalTagsDataSource
import ru.foodies.feature.tags.data.remote.AssetRemoteTagsDataSource
import ru.foodies.feature.tags.data.remote.RemoteTagsDataSource
import ru.foodies.feature.tags.domain.TagsRepository

internal val dataModule = module {
    singleOf(::InMemoryTagsDataSource) { bind<LocalTagsDataSource>() }
    singleOf(::AssetRemoteTagsDataSource) { bind<RemoteTagsDataSource>() }
    singleOf(::TagsRepositoryImpl) { bind<TagsRepository>() }
}