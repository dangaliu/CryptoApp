package com.example.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoapp.di.DaggerApplicationComponent
import com.example.cryptoapp.workers.RefreshDataWorkerFactory
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var refreshDataWorkerFactory: RefreshDataWorkerFactory

    val component by lazy { DaggerApplicationComponent.factory().create(this) }
    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(refreshDataWorkerFactory).build()
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}