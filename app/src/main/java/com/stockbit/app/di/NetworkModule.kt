package com.stockbit.app.di

import com.stockbit.app.data.remote.ApiService
import com.stockbit.app.data.repository.WatchlistRepositoryImpl
import com.stockbit.app.domain.repository.WatchlistRepository
import com.stockbit.app.domain.usecase.WatchlistUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

val NetworkModule = module {
    single { createService(get()) }
    single { createRetrofit(get(), "https://min-api.cryptocompare.com/") }
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

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun createWatchlistRepository(apiService: ApiService): WatchlistRepository {
    return WatchlistRepositoryImpl(apiService)
}

fun createWatchlistUseCase(repository: WatchlistRepository): WatchlistUseCase {
    return WatchlistUseCase(repository)
}