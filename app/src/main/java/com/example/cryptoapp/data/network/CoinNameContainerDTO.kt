package com.example.cryptoapp.data.network

import com.google.gson.annotations.SerializedName

data class CoinNameContainerDTO (
    @SerializedName("CoinInfo")
    val coinNameDTO: CoinNameDTO? = null
)
