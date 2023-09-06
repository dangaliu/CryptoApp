package com.example.cryptoapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_price_list")
data class CoinInfoDb(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String?,
    val price: String?,
    val highDay: String?,
    val lowDay: String?,
    val lastMarket: String?,
    val lastUpdate: Long?,
    val imageUrl: String
)
