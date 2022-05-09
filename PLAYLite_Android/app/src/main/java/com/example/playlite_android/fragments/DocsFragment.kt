package com.example.playlite_android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.playlite_android.R

class DocsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_docs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.manual_instalacion).setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, WebLinkFragment.newInstance(
                    "https://docs.google.com/viewer?url=https://www.grupoprilux.com/productos/01/tecnico/soluciones/cora-lite/manual/m-cora-lite.pdf"))
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.manual_pem).setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, WebLinkFragment.newInstance(
                    "https://docs.google.com/viewer?url=https://www.grupoprilux.com/productos/01/tecnico/soluciones/cora-lite/manual/m-cora-lite-pem.pdf"))
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DocsFragment().apply {
                arguments = Bundle().apply {

                }
        }
    }
}