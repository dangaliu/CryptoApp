package com.example.cryptoapp.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.cryptoapp.data.database.CoinDao
import com.example.cryptoapp.data.mappers.CoinMapper
import com.example.cryptoapp.data.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RefreshDataWorkerFactory @Inject constructor(
    private val coinInfoDao: CoinDao,
    private val coinMapper: CoinMapper,
    private val apiService: ApiService
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return DataRefreshWorker(
            appContext,
            workerParameters,
            coinInfoDao,
            coinMapper,
            apiService
        )
    }
}