package com.example.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoapp.di.DaggerApplicationComponent
import com.example.cryptoapp.workers.WorkerFactory
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: WorkerFactory

    val component by lazy { DaggerApplicationComponent.factory().create(this) }
    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory).build()
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}