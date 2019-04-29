package com.jml.random.users.app

import android.app.Application
import com.jml.random.users.di.appModule
import com.jml.random.users.di.repositoryModule
import com.jml.random.users.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RandomApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        startDI()
    }

    private fun startDI() {

        //TODO testing https://insert-koin.io/docs/2.0/documentation/reference/index.html#_checking_your_koin_configuration
        startKoin {
            androidLogger()
            androidContext(this@RandomApplication)
            modules(
                appModule,
                useCaseModule,
                repositoryModule
            )
        }
    }
}