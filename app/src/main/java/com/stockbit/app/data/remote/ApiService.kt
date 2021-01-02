package com.stockbit.app.data.remote

import com.stockbit.app.base.BaseResponse
import com.stockbit.app.data.model.Watchlist
import retrofit2.http.GET

interface ApiService {

    @GET("data/top/totaltoptiervolfull?limit=10&tsym=IDR")
    suspend fun getWatchlist(): BaseResponse<List<Watchlist>>
}