package com.example.playlite_android.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.example.playlite_android.R

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val WEB_LINK = "weblink"

class WebLinkFragment : Fragment() {

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(WEB_LINK)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_link, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myWebView: WebView = view.findViewById(R.id.docs_webview)
        myWebView.settings.javaScriptEnabled = true
        param1?.let { myWebView.loadUrl(it) }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1
         * @return A new instance of fragment webLinkFragment.
         */

        @JvmStatic
        fun newInstance(param1: String) =
            WebLinkFragment().apply {
                arguments = Bundle().apply {
                    putString(WEB_LINK, param1)
                }
            }
    }
}