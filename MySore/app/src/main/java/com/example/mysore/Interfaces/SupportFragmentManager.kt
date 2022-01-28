package com.example.mysore.Interfaces

import androidx.fragment.app.Fragment

interface SupportFragmentManager {

    fun setFragment(fragment: Fragment, param: String = "", param2: String = "")
}