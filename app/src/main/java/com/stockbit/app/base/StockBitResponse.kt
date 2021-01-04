package com.stockbit.app.base

import com.google.gson.annotations.SerializedName

data class StockBitResponse<T>(
    @SerializedName("message")
    val message : String? = null,
    @SerializedName("data")
    val data : T? = null
)