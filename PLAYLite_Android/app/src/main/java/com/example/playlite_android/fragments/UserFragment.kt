package com.example.playlite_android.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.playlite_android.R
import global.login.LoginGlobalCredentials
import global.login.LoginUserClass
import login.LoginMainActivity

/**
 * A simple [Fragment] subclass.
 * Use the [UserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_logout).setOnClickListener {
            val salir = Intent(activity, LoginMainActivity::class.java)
            activity?.startActivity(salir)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            UserFragment().apply {
                arguments = Bundle().apply {
//
                }
            }
    }
}