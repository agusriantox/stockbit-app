package com.stockbit.app.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stockbit.app.R
import com.stockbit.app.ui.portfolio.PortfolioViewModel

class WatchlistFragment : Fragment() {

    private lateinit var homeViewModel: WatchlistViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(WatchlistViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_watchlist, container, false)
        return root
    }
}