package com.stockbit.app.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("Message")
    val message : String? = null,
    @SerializedName("Type")
    val type : String? = null,
    @SerializedName("Data")
    val data : T? = null
)