package ru.vopros.foodies.domain.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.vopros.foodies.domain.usecase.AddProductToBasketUseCase
import ru.vopros.foodies.domain.usecase.GetBasketListFlowUseCase
import ru.vopros.foodies.domain.usecase.GetBasketSumFlowUseCase
import ru.vopros.foodies.domain.usecase.GetCategoryListFlowUseCase
import ru.vopros.foodies.domain.usecase.GetProductListFlowUseCase
import ru.vopros.foodies.domain.usecase.GetTagListFlowUseCase
import ru.vopros.foodies.domain.usecase.RemoveProductFromBasketUseCase

private val useCaseModule = module {
    singleOf(::GetCategoryListFlowUseCase)
    singleOf(::GetTagListFlowUseCase)
    singleOf(::GetProductListFlowUseCase)
    singleOf(::GetBasketListFlowUseCase)
    singleOf(::GetBasketSumFlowUseCase)
    singleOf(::AddProductToBasketUseCase)
    singleOf(::RemoveProductFromBasketUseCase)
}

val domainModule = module {
    includes(useCaseModule)
}