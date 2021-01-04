package com.stockbit.app.app

import android.app.Application
import androidx.multidex.MultiDex
import com.stockbit.app.di.AppModule
import com.stockbit.app.di.NetworkModule
import io.paperdb.Paper
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class StockbitApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        Paper.init(this)

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@StockbitApp)
            modules(listOf(AppModule, NetworkModule))
        }
    }
}