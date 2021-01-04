package com.stockbit.app.domain.usecase

import com.stockbit.app.base.StockBitResponse
import com.stockbit.app.base.UseCase
import com.stockbit.app.data.model.Auth
import com.stockbit.app.data.model.User
import com.stockbit.app.domain.repository.StockbitRepository

class StockbitLoginUseCase constructor(
    private val repository: StockbitRepository
) : UseCase<StockBitResponse<Auth>, User>() {

    override suspend fun run(params: User?): StockBitResponse<Auth> {
        return repository.auth(
            user = params?.username,
            password = params?.password
        )
    }

}