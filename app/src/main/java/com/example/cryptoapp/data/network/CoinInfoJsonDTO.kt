package com.example.cryptoapp.data.network

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CoinInfoJsonDTO (
    @SerializedName("RAW")
    val json: JsonObject? = null
)
