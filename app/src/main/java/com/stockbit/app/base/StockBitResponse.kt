package com.stockbit.app.base

import com.google.gson.annotations.SerializedName

data class StockBitResponse<T>(
    @SerializedName("message")
    var message : String? = null,
    @SerializedName("data")
    var data : T? = null
)