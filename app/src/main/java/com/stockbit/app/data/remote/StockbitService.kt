package com.stockbit.app.data.remote

import com.stockbit.app.base.StockBitResponse
import com.stockbit.app.data.model.Auth
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface StockbitService {

    @FormUrlEncoded
    @POST("v2.4/login")
    suspend fun auth(
        @Field("user") user: String?,
        @Field("password") password: String?
    ): StockBitResponse<Auth>

}