package com.example.cryptoapp.data.network

import com.google.gson.annotations.SerializedName

data class CoinNameDTO (
    @SerializedName("Name")
    val name: String? = null
)
