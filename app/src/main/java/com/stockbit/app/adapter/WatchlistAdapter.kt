package com.stockbit.app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.app.R
import com.stockbit.app.data.model.Watchlist
import com.stockbit.app.databinding.HolderWatchlistBinding
import java.text.DecimalFormat
import kotlin.properties.Delegates

class WatchlistAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: List<Watchlist> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.holder_watchlist, parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = if (list.isNullOrEmpty()) 0 else list.size

    private fun getItem(position: Int): Watchlist = list[position]

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).onBind(getItem(position))
    }

    private inner class MyViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(data: Watchlist) {
            (binding as HolderWatchlistBinding).apply {
                tvName.text = data.coinInfo?.name
                tvFullName.text = data.coinInfo?.fullName
                tvPrice.text = data.display?.idr?.price
                data.raw?.idr?.let {
                    val updatedPrice = it.price - it.openDay
                    val updatedPricePercentage = updatedPrice / it.openDay * 100

                    val df = DecimalFormat("+#,##0.00;-#")
                    tvUpdatedPrice.text = "${df.format(updatedPrice)}(${df.format(updatedPricePercentage)}%)"

                    when {
                        updatedPrice > 0 -> tvUpdatedPrice.setTextColor(ContextCompat.getColor(root.context, R.color.price_gain))
                        updatedPrice < 0 -> tvUpdatedPrice.setTextColor(ContextCompat.getColor(root.context, R.color.price_loss))
                        else -> tvUpdatedPrice.setTextColor(ContextCompat.getColor(root.context, R.color.price_equal))
                    }
                }
            }
        }

    }

}

