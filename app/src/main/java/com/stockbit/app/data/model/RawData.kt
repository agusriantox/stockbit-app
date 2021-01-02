package com.stockbit.app.data.model

import com.google.gson.annotations.SerializedName

data class RawData(
    @SerializedName("PRICE")
    var price: Double,
    @SerializedName("OPENDAY")
    var openDay: Double
)