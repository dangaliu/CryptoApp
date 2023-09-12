package com.example.cryptoapp.di

import com.example.cryptoapp.workers.ChildWorkerFactory
import com.example.cryptoapp.workers.DataRefreshWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(DataRefreshWorker::class)
    fun bindDataRefreshWorkerFactory(impl: DataRefreshWorker.Factory): ChildWorkerFactory

}