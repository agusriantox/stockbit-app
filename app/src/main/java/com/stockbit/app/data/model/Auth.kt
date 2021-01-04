package com.stockbit.app.data.model

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("access_user")
    var accessUser : Auth? = null,
    @SerializedName("access_token")
    var accessToken : String? = null,
    @SerializedName("access_token_exp")
    var accessTokenExp : Long? = null,
    @SerializedName("refresh_token")
    var refreshToken : String? = null
)