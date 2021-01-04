package com.stockbit.app.data.repository

import com.stockbit.app.base.StockBitResponse
import com.stockbit.app.data.model.Auth
import com.stockbit.app.data.remote.StockbitService
import com.stockbit.app.domain.repository.StockbitRepository

class StockbitRepositoryImpl(private val service: StockbitService) : StockbitRepository {

    override suspend fun auth(user: String?, password: String?): StockBitResponse<Auth> {
        return service.auth(user, password)
    }

}