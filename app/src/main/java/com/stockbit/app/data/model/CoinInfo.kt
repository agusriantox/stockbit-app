package com.stockbit.app.data.model

import com.google.gson.annotations.SerializedName

data class CoinInfo(
    @SerializedName("Id")
    var id: Int? = null,
    @SerializedName("Name")
    var name: String? = null,
    @SerializedName("FullName")
    var fullName: String? = null
)