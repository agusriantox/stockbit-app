package com.stockbit.app.data.model

import com.google.gson.annotations.SerializedName

data class DisplayData(
    @SerializedName("PRICE")
    var price: String,
    @SerializedName("OPENDAY")
    var openDay: String
)