package com.stockbit.app.ui.watchlist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.stockbit.app.R
import com.stockbit.app.adapter.WatchlistAdapter
import com.stockbit.app.base.DataBindingFragment
import com.stockbit.app.databinding.FragmentWatchlistBinding
import kotlinx.android.synthetic.main.fragment_watchlist.*
import org.koin.android.viewmodel.ext.android.viewModel

class WatchlistFragment : DataBindingFragment<FragmentWatchlistBinding>() {

    private val watchlistViewModel: WatchlistViewModel by viewModel()

    override fun layoutId(): Int = R.layout.fragment_watchlist

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = WatchlistAdapter()
        rv_watchlist.adapter = adapter

        watchlistViewModel.getWatchList()
        watchlistViewModel.watchlist().observe(viewLifecycleOwner, Observer {
            adapter.list = it
        })
    }
}