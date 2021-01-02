package com.stockbit.app.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockbit.app.base.*
import com.stockbit.app.data.model.Watchlist
import com.stockbit.app.domain.usecase.WatchlistUseCase
import kotlinx.coroutines.cancel

class WatchlistViewModel constructor(private val watchlistUseCase: WatchlistUseCase) : ViewModel() {

    companion object {
        private val TAG = WatchlistViewModel::class.java.name
    }

    var page = 1

    private val _watchlist = MutableLiveData<Data<List<Watchlist>>>()
    fun getWatchlist(): LiveData<Data<List<Watchlist>>> = _watchlist

    fun onRefresh(){
        page = 1
        fetchWatchlist()
    }

    fun fetchWatchlist() {
        _watchlist.value = Data(state = State.LOADING)
        watchlistUseCase.invoke(viewModelScope, page, object : UseCaseResponse<BaseResponse<List<Watchlist>>> {
                override fun onSuccess(result: BaseResponse<List<Watchlist>>) {
                    page += 1
                    _watchlist.value = Data(state = State.SUCCESS, data = result.data)
                }

                override fun onError(apiError: ApiError?) {
                    _watchlist.value = Data(state = State.ERROR, message = apiError?.getErrorMessage())
                }
            })
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}