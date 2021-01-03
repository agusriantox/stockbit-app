package com.stockbit.app.domain.usecase

import com.stockbit.app.base.BaseResponse
import com.stockbit.app.base.UseCase
import com.stockbit.app.data.model.Watchlist
import com.stockbit.app.domain.repository.WatchlistRepository

class WatchlistUseCase constructor(
    private val watchlistRepository: WatchlistRepository
) : UseCase<BaseResponse<List<Watchlist>>, Int>() {

    override suspend fun run(params: Int?): BaseResponse<List<Watchlist>> {
        return watchlistRepository.fetchWatchlist(
            limit = 20,
            page = params ?: 1,
            tsym = "IDR"
        )
    }

}