package com.example.cryptoapp.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.cryptoapp.data.database.CoinDao
import com.example.cryptoapp.data.mappers.CoinMapper
import com.example.cryptoapp.data.network.ApiService
import kotlinx.coroutines.delay

class DataRefreshWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val coinInfoDao: CoinDao,
    private val coinMapper: CoinMapper,
    private val apiService: ApiService
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = coinMapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = coinMapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { coinMapper.mapDtoModelToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
                delay(10000)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        const val NAME = "DataRefreshWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<DataRefreshWorker>().build()
        }
    }
}