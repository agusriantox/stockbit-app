package com.stockbit.app.domain.repository

import com.stockbit.app.base.BaseResponse
import com.stockbit.app.data.model.Watchlist

interface WatchlistRepository {
    suspend fun fetchWatchlist(limit: Int, page: Int, tsym: String): BaseResponse<List<Watchlist>>
}