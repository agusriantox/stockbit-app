package com.stockbit.app.data.repository

import com.stockbit.app.base.BaseResponse
import com.stockbit.app.data.model.Watchlist
import com.stockbit.app.data.remote.CryptoCompareService
import com.stockbit.app.domain.repository.WatchlistRepository

class WatchlistRepositoryImpl(private val service: CryptoCompareService) : WatchlistRepository {

    override suspend fun fetchWatchlist(
        limit: Int,
        page: Int,
        tsym: String
    ): BaseResponse<List<Watchlist>> {
        return service.getWatchlist(limit, page, tsym)
    }

}