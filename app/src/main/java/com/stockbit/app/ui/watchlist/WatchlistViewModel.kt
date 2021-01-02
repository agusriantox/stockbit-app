package com.stockbit.app.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockbit.app.base.ApiError
import com.stockbit.app.base.BaseResponse
import com.stockbit.app.base.UseCaseResponse
import com.stockbit.app.data.model.Watchlist
import com.stockbit.app.domain.usecase.WatchlistUseCase
import kotlinx.coroutines.cancel

class WatchlistViewModel constructor(private val watchlistUseCase: WatchlistUseCase) : ViewModel() {

    companion object {
        private val TAG = WatchlistViewModel::class.java.name
    }

    private val _watchlist = MutableLiveData<List<Watchlist>>()
    fun watchlist() : LiveData<List<Watchlist>> = _watchlist

    fun getWatchList() {
        watchlistUseCase.invoke(viewModelScope, null, object : UseCaseResponse<BaseResponse<List<Watchlist>>> {
            override fun onSuccess(result: BaseResponse<List<Watchlist>>) {
                _watchlist.value = result.data
            }

            override fun onError(apiError: ApiError?) {
            }
        })
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}