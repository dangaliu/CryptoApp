package com.example.cryptoapp.data.network

import com.google.gson.annotations.SerializedName

data class CoinNamesListDTO (
    @SerializedName("Data")
    val data: List<CoinNameContainerDTO>? = null
)
