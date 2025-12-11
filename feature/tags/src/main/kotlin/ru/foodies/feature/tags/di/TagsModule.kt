package ru.foodies.feature.tags.di

import org.koin.dsl.module

val tagsFeatureModule = module {
    includes(
        dataModule,
        domainModule,
        presentationModule
    )
}
