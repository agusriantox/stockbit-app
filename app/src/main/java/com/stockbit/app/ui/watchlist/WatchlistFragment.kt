package com.stockbit.app.ui.watchlist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.stockbit.app.R
import com.stockbit.app.adapter.WatchlistAdapter
import com.stockbit.app.base.DataBindingFragment
import com.stockbit.app.base.State
import com.stockbit.app.databinding.FragmentWatchlistBinding
import org.koin.android.viewmodel.ext.android.viewModel

class WatchlistFragment : DataBindingFragment<FragmentWatchlistBinding>() {

    private val watchlistViewModel: WatchlistViewModel by viewModel()

    override fun layoutId(): Int = R.layout.fragment_watchlist

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getWatchList()
    }

    private fun initView() {
        binding.swipeRefresh.setOnRefreshListener {
            watchlistViewModel.fetchWatchlist()
        }

        binding.btnRetry.setOnClickListener {
            watchlistViewModel.fetchWatchlist()
        }
    }

    private fun getWatchList() {
        val adapter = WatchlistAdapter()
        binding.rvWatchlist.adapter = adapter

        watchlistViewModel.fetchWatchlist()
        watchlistViewModel.getWatchlist().observe(viewLifecycleOwner, Observer { result ->
            when (result.state) {
                State.LOADING -> {
                    binding.swipeRefresh.isRefreshing = true
                    binding.layoutStateEmpty.isVisible = false
                    binding.layoutStateError.isVisible = false
                }
                State.SUCCESS -> {
                    binding.swipeRefresh.isRefreshing = false
                    if (result.data.isNullOrEmpty()) {
                        binding.rvWatchlist.isVisible = false
                        binding.layoutStateEmpty.isVisible = true
                    } else {
                        binding.rvWatchlist.isVisible = true
                        adapter.list = result.data
                    }
                }
                State.ERROR -> {
                    binding.swipeRefresh.isRefreshing = false
                    binding.layoutStateError.isVisible = true
                    binding.rvWatchlist.isVisible = false
                }
            }
        })


    }

}