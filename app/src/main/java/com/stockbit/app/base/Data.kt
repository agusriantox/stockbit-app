package com.stockbit.app.base

data class Data<T>(val state: State, val data: T? = null, val message: String? = null)