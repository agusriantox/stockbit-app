package com.stockbit.app.di

import com.stockbit.app.ui.login.LoginViewModel
import com.stockbit.app.ui.main.watchlist.WatchlistViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { WatchlistViewModel(get()) }

    viewModel { LoginViewModel(get()) }

    single { createWatchlistRepository(get()) }

    single { createWatchlistUseCase(get()) }

    single { createStockBitRepository(get()) }

    single { createStockbitLoginUseCase(get()) }
}