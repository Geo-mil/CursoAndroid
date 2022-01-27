package com.example.mysore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.add
import com.example.mysore.Modelos.User
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class StoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        setFragment(StoreMenuFragment())
        navigation_view ()

        val bundle:Bundle? = intent.extras
        val email = bundle?.get("User e-mail")
        Toast.makeText(this,email.toString()+" signed in", Toast.LENGTH_SHORT).show()
        createUser(email.toString())

        val SignOut = findViewById<Button>(R.id.button)

        SignOut.setOnClickListener { view: View? ->
            Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }

        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<StoreMenuFragment>(R.id.menu_fragment)
            }
        }
    }

    private fun navigation_view (){

        findViewById<BottomNavigationView>(R.id.botomNavBar).setOnItemSelectedListener {

            when(it.itemId){
                R.id.home -> {
                    setFragment(StoreMenuFragment())
                }
                R.id.shopping_car -> {
                    setFragment(ShoopingCarMenuFragment())
                }
            }

            return@setOnItemSelectedListener true
        }

    }

    private fun setFragment (fragment: Fragment){
        supportFragmentManager.commit {
            replace(R.id.menu_fragment, fragment, fragment.tag)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    private fun createUser(email: String){
        var newUser = User(null,null,null,email)
    }

}