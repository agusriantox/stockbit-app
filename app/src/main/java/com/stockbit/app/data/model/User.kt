package com.stockbit.app.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("username")
    var username : String? = null,
    @SerializedName("email")
    var email : String? = null,
    @SerializedName("fullname")
    var fullName : String? = null,
    @SerializedName("avatar")
    var avatar : String? = null,
    @SerializedName("password")
    var password : String? = null
)