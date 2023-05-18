package com.vopros.foodies.di

import android.content.Context
import com.vopros.foodies.data.BasketRepositoryImpl
import com.vopros.foodies.data.CategoryRepositoryImpl
import com.vopros.foodies.data.FiltersRepositoryImpl
import com.vopros.foodies.data.ProductRepositoryImpl
import com.vopros.foodies.data.TagRepositoryImpl
import com.vopros.foodies.domain.basket.BasketRepository
import com.vopros.foodies.domain.filter.FiltersRepository
import com.vopros.foodies.domain.category.Category
import com.vopros.foodies.domain.product.Product
import com.vopros.foodies.domain.Repository
import com.vopros.foodies.domain.tag.Tag
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideProductRepository(
        @ApplicationContext context: Context
    ): Repository<Product> = ProductRepositoryImpl(context)

    @Singleton
    @Provides
    fun provideCategoryRepository(
        @ApplicationContext context: Context
    ): Repository<Category> = CategoryRepositoryImpl(context)

    @Singleton
    @Provides
    fun provideTagRepository(
        @ApplicationContext context: Context
    ): Repository<Tag> = TagRepositoryImpl(context)

    @Singleton
    @Provides
    fun provideBasket(): BasketRepository = BasketRepositoryImpl()

    @Singleton
    @Provides
    fun provideFiltersRepository(): FiltersRepository = FiltersRepositoryImpl()

}