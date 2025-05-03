package ru.vopros.foodies

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.vopros.foodies.data.di.dataModule
import ru.vopros.foodies.domain.di.domainModule
import ru.vopros.foodies.presentation.di.presentationModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                dataModule,
                domainModule,
                presentationModule,
            )
        }
    }

}