package com.example.cryptoapp.di

import android.app.Application
import com.example.cryptoapp.presentation.view.CoinDetailFragment
import com.example.cryptoapp.presentation.view.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)
    fun inject(fragment: CoinDetailFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance
            application: Application
        ): ApplicationComponent
    }
}