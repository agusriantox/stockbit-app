package com.stockbit.app.ui.main.stream

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StreamViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Stream Fragment"
    }
    val text: LiveData<String> = _text
}