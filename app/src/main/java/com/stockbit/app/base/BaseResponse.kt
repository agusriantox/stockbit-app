package com.stockbit.app.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("Message")
    var message : String? = null,
    @SerializedName("Type")
    var type : String? = null,
    @SerializedName("Data")
    var data : T? = null
)