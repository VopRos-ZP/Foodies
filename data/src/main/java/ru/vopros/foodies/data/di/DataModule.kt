package ru.vopros.foodies.data.di

import androidx.room.Room
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.vopros.foodies.data.repository.BasketRepositoryImpl
import ru.vopros.foodies.data.repository.LocalCategoryRepositoryImpl
import ru.vopros.foodies.data.repository.LocalProductRepositoryImpl
import ru.vopros.foodies.data.repository.LocalTagRepositoryImpl
import ru.vopros.foodies.data.repository.RemoteCategoryRepositoryImpl
import ru.vopros.foodies.data.repository.RemoteProductRepositoryImpl
import ru.vopros.foodies.data.repository.RemoteTagRepositoryImpl
import ru.vopros.foodies.data.room.AppDatabase
import ru.vopros.foodies.domain.repository.BasketRepository
import ru.vopros.foodies.domain.repository.LocalCategoryRepository
import ru.vopros.foodies.domain.repository.LocalProductRepository
import ru.vopros.foodies.domain.repository.LocalTagRepository
import ru.vopros.foodies.domain.repository.RemoteCategoryRepository
import ru.vopros.foodies.domain.repository.RemoteProductRepository
import ru.vopros.foodies.domain.repository.RemoteTagRepository

private val roomModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            context = get(),
            klass = AppDatabase::class.java,
            name = AppDatabase.NAME
        ).build()
    }
}

private val repositoryModule = module {
    singleOf(::LocalCategoryRepositoryImpl) { bind<LocalCategoryRepository>() }
    singleOf(::RemoteCategoryRepositoryImpl) { bind<RemoteCategoryRepository>() }

    singleOf(::LocalTagRepositoryImpl) { bind<LocalTagRepository>() }
    singleOf(::RemoteTagRepositoryImpl) { bind<RemoteTagRepository>() }

    singleOf(::RemoteProductRepositoryImpl) { bind<RemoteProductRepository>() }
    singleOf(::LocalProductRepositoryImpl) { bind<LocalProductRepository>() }

    singleOf(::BasketRepositoryImpl) { bind<BasketRepository>() }
}

val dataModule = module {
    includes(
        roomModule,
        repositoryModule
    )
}