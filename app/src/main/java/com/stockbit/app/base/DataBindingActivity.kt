package com.stockbit.app.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class DataBindingActivity<VB : ViewDataBinding> : BaseActivity() {

    lateinit var binding: VB

    override fun setContentView(layoutResID: Int) {
        binding = DataBindingUtil.inflate(layoutInflater, layoutResID, null, false)
        super.setContentView(binding.root)

    }
}