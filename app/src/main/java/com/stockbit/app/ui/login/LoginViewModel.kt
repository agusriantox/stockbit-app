package com.stockbit.app.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockbit.app.base.*
import com.stockbit.app.data.model.Auth
import com.stockbit.app.data.model.User
import com.stockbit.app.domain.usecase.StockbitLoginUseCase
import kotlinx.coroutines.cancel

class LoginViewModel constructor(private val useCase: StockbitLoginUseCase) : ViewModel() {

    companion object {
        private val TAG = LoginViewModel::class.java.name
    }

    val userField = ObservableField<String>()
    val passwordField = ObservableField<String>()

    private val _loginResult = MutableLiveData<Data<Auth>>()
    fun getLoginResult(): LiveData<Data<Auth>> = _loginResult


    fun doLogin(){
        if (isInvalidInput() || _loginResult.value?.state == State.LOADING){
            return
        }

        _loginResult.value = Data(state = State.LOADING)
        useCase.invoke(viewModelScope, User(username = userField.get(), password = passwordField.get()), object : UseCaseResponse<StockBitResponse<Auth>> {
            override fun onSuccess(result: StockBitResponse<Auth>) {
                if (result.data != null) {
                    _loginResult.value = Data(state = State.SUCCESS, data = result.data)
                } else {
                    _loginResult.value = Data(state = State.ERROR, message = result.message)
                }
            }

            override fun onError(apiError: ApiError?) {
                _loginResult.value = Data(state = State.ERROR, message = apiError?.getErrorMessage())
            }
        })
    }

    private fun isInvalidInput() : Boolean {
        return userField.get().isNullOrEmpty() || passwordField.get().isNullOrEmpty()
    }


    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}