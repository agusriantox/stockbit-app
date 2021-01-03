package com.stockbit.app.ui.main.watchlist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.app.R
import com.stockbit.app.adapter.WatchlistAdapter
import com.stockbit.app.base.DataBindingFragment
import com.stockbit.app.base.State
import com.stockbit.app.databinding.FragmentWatchlistBinding
import com.stockbit.app.utils.WrapContentLinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel

class WatchlistFragment : DataBindingFragment<FragmentWatchlistBinding>() {

    private val watchlistViewModel: WatchlistViewModel by viewModel()

    val adapter = WatchlistAdapter()

    override fun layoutId(): Int = R.layout.fragment_watchlist

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getWatchList()
    }

    private fun initView() {
        binding.swipeRefresh.setOnRefreshListener {
            adapter.list = arrayListOf()
            watchlistViewModel.onRefresh()
        }

        binding.btnRetry.setOnClickListener {
            watchlistViewModel.fetchWatchlist()
        }

        val layoutManager = WrapContentLinearLayoutManager(context)
        binding.rvWatchlist.layoutManager = layoutManager
        binding.rvWatchlist.adapter = adapter

        binding.rvWatchlist.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()

                if (watchlistViewModel.getWatchlist().value?.state != State.LOADING && lastVisiblePosition == adapter.list.size - 1) {
                    watchlistViewModel.fetchWatchlist()
                }

            }
        })
    }

    private fun getWatchList() {
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
                        adapter.insertData(result.data)
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