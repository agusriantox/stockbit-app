package com.stockbit.app.di

import com.stockbit.app.ui.watchlist.WatchlistViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { WatchlistViewModel(get()) }

    single { createWatchlistRepository(get()) }

    single { createWatchlistUseCase(get()) }

}