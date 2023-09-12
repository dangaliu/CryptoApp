package com.example.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptoapp.data.database.CoinDao
import com.example.cryptoapp.data.mappers.CoinMapper
import com.example.cryptoapp.domain.model.CoinInfo
import com.example.cryptoapp.domain.repository.CoinRepository
import com.example.cryptoapp.workers.DataRefreshWorker
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val application: Application,
    private val coinInfoDao: CoinDao,
    private val coinMapper: CoinMapper
) : CoinRepository {

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getCoinInfoList()) { dbList ->
            dbList.map { coinMapper.mapDbModelToEntity(it) }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)) {
            coinMapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            DataRefreshWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            DataRefreshWorker.makeRequest()
        )
    }
}