package com.stockbit.app.data.repository

import com.stockbit.app.base.BaseResponse
import com.stockbit.app.data.model.Watchlist
import com.stockbit.app.data.remote.ApiService
import com.stockbit.app.domain.repository.WatchlistRepository

class WatchlistRepositoryImpl(private val service: ApiService) : WatchlistRepository {

    override suspend fun fetchWatchlist(): BaseResponse<List<Watchlist>> {
        return service.getWatchlist()
    }

}