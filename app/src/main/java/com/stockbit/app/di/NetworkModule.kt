package com.stockbit.app.di

import com.stockbit.app.data.remote.CryptoCompareService
import com.stockbit.app.data.remote.StockbitService
import com.stockbit.app.data.repository.StockbitRepositoryImpl
import com.stockbit.app.data.repository.WatchlistRepositoryImpl
import com.stockbit.app.domain.repository.StockbitRepository
import com.stockbit.app.domain.repository.WatchlistRepository
import com.stockbit.app.domain.usecase.StockbitLoginUseCase
import com.stockbit.app.domain.usecase.WatchlistUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

val NetworkModule = module {
    single { createCryptoCompareService(get(), "https://min-api.cryptocompare.com/") }
    single { createStockBitService(get(), "https://api.stockbit.com/") }
    single { createOkHttpClient() }
    single { GsonConverterFactory.create() }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createCryptoCompareService(okHttpClient: OkHttpClient, url: String): CryptoCompareService {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CryptoCompareService::class.java)}

fun createWatchlistRepository(apiService: CryptoCompareService): WatchlistRepository {
    return WatchlistRepositoryImpl(apiService)
}

fun createWatchlistUseCase(repository: WatchlistRepository): WatchlistUseCase {
    return WatchlistUseCase(repository)
}

fun createStockBitService(okHttpClient: OkHttpClient, url: String): StockbitService {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(StockbitService::class.java)
}

fun createStockBitRepository(apiService: StockbitService): StockbitRepository {
    return StockbitRepositoryImpl(apiService)
}

fun createStockbitLoginUseCase(repository: StockbitRepository): StockbitLoginUseCase {
    return StockbitLoginUseCase(repository)
}