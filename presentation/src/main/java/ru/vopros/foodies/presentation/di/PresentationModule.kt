package ru.vopros.foodies.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.vopros.foodies.presentation.screens.basket.BasketViewModel
import ru.vopros.foodies.presentation.screens.catalog.CatalogViewModel
import ru.vopros.foodies.presentation.screens.details.ProductDetailsViewModel
import ru.vopros.foodies.presentation.screens.splash.SplashViewModel

private val viewModelModule = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::CatalogViewModel)
    viewModelOf(::ProductDetailsViewModel)
    viewModelOf(::BasketViewModel)
}

val presentationModule = module {
    includes(
        viewModelModule
    )
}