package com.example.cryptoapp.data.mappers

import com.example.cryptoapp.data.database.CoinInfoDb
import com.example.cryptoapp.data.network.CoinInfoDTO
import com.example.cryptoapp.data.network.CoinInfoJsonDTO
import com.example.cryptoapp.data.network.CoinNamesListDTO
import com.example.cryptoapp.domain.CoinInfo
import com.google.gson.Gson

class CoinMapper {

    fun mapDtoModelToDbModel(coinInfoDTO: CoinInfoDTO): CoinInfoDb {
        return CoinInfoDb(
            fromSymbol = coinInfoDTO.fromSymbol,
            toSymbol = coinInfoDTO.toSymbol,
            price = coinInfoDTO.price,
            highDay = coinInfoDTO.highDay,
            lowDay = coinInfoDTO.lowDay,
            lastMarket = coinInfoDTO.lastMarket,
            lastUpdate = coinInfoDTO.lastUpdate,
            imageUrl = coinInfoDTO.imageUrl
        )
    }

    fun mapDbModelToEntity(coinInfoDb: CoinInfoDb): CoinInfo {
        return CoinInfo(
            fromSymbol = coinInfoDb.fromSymbol,
            toSymbol = coinInfoDb.toSymbol,
            price = coinInfoDb.price,
            highDay = coinInfoDb.highDay,
            lowDay = coinInfoDb.lowDay,
            lastMarket = coinInfoDb.lastMarket,
            lastUpdate = coinInfoDb.lastUpdate,
            imageUrl = coinInfoDb.imageUrl
        )
    }

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonDTO): List<CoinInfoDTO> {
        val result = ArrayList<CoinInfoDTO>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDTO::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesListDTO: CoinNamesListDTO): String {
        return namesListDTO.data?.map {
            it.coinNameDTO?.name
        }?.joinToString(",") ?: ""
    }
}