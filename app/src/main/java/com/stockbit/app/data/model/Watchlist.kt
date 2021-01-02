package com.stockbit.app.data.model

import com.google.gson.annotations.SerializedName

data class Watchlist(
    @SerializedName("CoinInfo")
    var coinInfo: CoinInfo? = null,
    @SerializedName("RAW")
    var raw: Raw? = null,
    @SerializedName("DISPLAY")
    var display: Display? = null
) {
    data class Raw(
        @SerializedName("IDR")
        var idr: RawData? = null
    )

    data class Display(
        @SerializedName("IDR")
        var idr: DisplayData? = null
    )
}