package com.stockbit.app.data.remote

import com.stockbit.app.base.BaseResponse
import com.stockbit.app.data.model.Watchlist
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/top/totaltoptiervolfull")
    suspend fun getWatchlist(
        @Query("limit") limit : Int,
        @Query("page") page : Int,
        @Query("tsym") tsym : String
    ): BaseResponse<List<Watchlist>>
}