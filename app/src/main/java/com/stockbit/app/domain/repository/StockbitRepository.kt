package com.stockbit.app.domain.repository

import com.stockbit.app.base.StockBitResponse
import com.stockbit.app.data.model.Auth

interface StockbitRepository {
    suspend fun auth(user: String?, password: String?): StockBitResponse<Auth>
}