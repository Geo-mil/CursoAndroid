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
import com.example.mysore.Connection.APIStore
import com.example.mysore.Interfaces.SupportFragmentManager
import com.example.mysore.Modelos.Items
import com.example.mysore.Modelos.User
import com.example.mysore.Utils.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Call
import java.io.IOException


class StoreActivity : AppCompatActivity(), SupportFragmentManager {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        //inicializar fragmentos
        //setFragment(StoreMenuFragment())
        navigation_view ()

        //recuperar datos enviados por la activity anterior
        val bundle:Bundle? = intent.extras
        val email = bundle?.get("User e-mail")
        Toast.makeText(this,email.toString()+" signed in", Toast.LENGTH_SHORT).show()

        // TODO: aqui sacamos la lista de la compra del user
        //createUser(email.toString(), )// TODO: aqui se la añadimos al crear al user

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

    override fun setFragment(fragment: Fragment, param: String, param2: String) {
        val bundle = bundleOf()
        bundle.putString(Constants.ITEM_ID_EXTRA, param)
        bundle.putString(Constants.ITEM_PRICE, param2)
        fragment.arguments = bundle
//        if(fragment.id == supportFragmentManager.findFragmentById(StoreMenuFragment().id)?.id) {
//            supportFragmentManager.popBackStack(fragment.id, 0)
//        }else{
            supportFragmentManager.commit {
                replace(R.id.menu_fragment, fragment, fragment.tag)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
 //       }
    }

    /*fun sacardatos() {
        val request = Request.Builder()
            .url("https://digimon-api.vercel.app/api/digimon")
            .build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                // progress.visibility = View.GONE
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                var str_response = response.body()!!.string()
            }
        })

    }*/

    /*   var retrofit = Retrofit.Builder()
           .baseUrl("https://digimon-api.vercel.app/")
           .addConverterFactory(
               GsonConverterFactory.create(
                   GsonBuilder().serializeNulls().create()
               )
           )
           .build()
       var digimonApiService: APIStore.APIService = retrofit.create(APIStore.APIService::class.java)
       var call: Call = digimonApiService.getListOfItems("api/digimon")

       private fun createUser(email: String, listaCompra: List<Items>){
           val idgen = Math.random()*10000
           var newUser = User(email =  email,
                              articles = listaCompra,
                              id = idgen.toInt()
           )
       }*/



}