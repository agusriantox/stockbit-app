package com.stockbit.app.ui.stream

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stockbit.app.R

class StreamFragment : Fragment() {

    private lateinit var streamViewModel: StreamViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        streamViewModel =
            ViewModelProvider(this).get(StreamViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_stream, container, false)
        val textView: TextView = root.findViewById(R.id.text_menu)
        streamViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}