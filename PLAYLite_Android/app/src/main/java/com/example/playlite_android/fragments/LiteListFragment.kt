package com.example.playlite_android.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.playlite_android.R

class LiteListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lite_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = LiteListFragment().apply {
                arguments = Bundle().apply {

                }
        }
    }



}