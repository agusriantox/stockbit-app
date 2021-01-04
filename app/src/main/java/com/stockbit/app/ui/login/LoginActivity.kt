package com.stockbit.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.stockbit.app.R
import com.stockbit.app.base.DataBindingActivity
import com.stockbit.app.base.State
import com.stockbit.app.databinding.ActivityLoginBinding
import com.stockbit.app.ui.main.MainActivity
import com.stockbit.app.utils.gone
import com.stockbit.app.utils.setupPasswordToggle
import com.stockbit.app.utils.visible
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : DataBindingActivity<ActivityLoginBinding>() {

    private val loginViewModel : LoginViewModel by viewModel()

    override fun layoutId(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())

        initView()
        setupLogin()
    }

    private fun initView() {
        binding.vm = loginViewModel

        binding.etPassword.setupPasswordToggle()

        binding.btnLoginProgress.gone()
        binding.btnLogin.setOnClickListener {
            loginViewModel.doLogin()
        }

    }

    private fun setupLogin() {
        loginViewModel.getLoginResult().observe(this, Observer { result ->
            when(result.state){
                State.LOADING -> {
                    binding.btnLoginProgress.visible()
                }
                State.SUCCESS -> {
                    binding.btnLoginProgress.gone()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                State.ERROR -> {
                    binding.btnLoginProgress.gone()
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}