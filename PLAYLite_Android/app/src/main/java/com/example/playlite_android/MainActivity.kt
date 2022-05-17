package com.example.playlite_android

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.playlite_android.fragments.DocsFragment
import com.example.playlite_android.fragments.LiteListFragment
import com.example.playlite_android.fragments.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main)

        val bottomBar = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //accedemos al fragment principal al crear la activity
        openFragment(LiteListFragment.newInstance())

        //dependiendo de la selección en el menú inferior accedemos a un fragment o a otro
        bottomBar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    // Respond to navigation item 1 click
                    if (savedInstanceState == null) {
                            openFragment(LiteListFragment.newInstance())
                    }
                    true
                }
                R.id.docu -> {
                    // Respond to navigation item 2 click
                    if (savedInstanceState == null) {
                            openFragment(DocsFragment.newInstance())
                    }
                    true
                }
                R.id.user -> {
                    // Respond to navigation item 3 click
                    if (savedInstanceState == null) {
                            openFragment(UserFragment.newInstance())
                    }
                    true
                }
                else -> false
            }
        }

    }

    //transicion entre fragments
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setReorderingAllowed(true)
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
            //additional code
        } else {
            supportFragmentManager.popBackStack()
        }
    }

}