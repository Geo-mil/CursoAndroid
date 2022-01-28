package com.example.mysore.Connection

import android.util.Log
import com.example.mysore.Adapter.StoreAdapter
import com.example.mysore.Modelos.Items
import com.example.mysore.StoreMenuFragment
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener
import java.io.IOException


class APIStore {

    private val BASE_URL = "https://digimon-api.vercel.app/api/digimon"
    private val TAG = "APIStore_jsonparse"

    fun sacardatos() {

        val request = Request.Builder()
            .url(BASE_URL)
            .build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                // progress.visibility = View.GONE
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                val str_response = response.body()!!.string()

                json_to_item(str_response)
            }
        })
    }

    //cargar el fichero JSON con las categorias

    fun json_to_item (jsonCategoryString : String) {

        Log.i(TAG, jsonCategoryString)

        val nueva_string: String = "{\"Digimones\": " + jsonCategoryString +"}"

        var jsonObject = JSONObject()
        try {
            jsonObject = JSONTokener(nueva_string).nextValue() as JSONObject
        } catch (I: IOException) {
            Log.i(TAG, I.cause.toString())
        }

        val jsonArray = jsonObject.getJSONArray("Digimones")

        Log.i(TAG, jsonArray.length().toString())
        for (i in 0 until jsonArray.length()) {
            val imagen = jsonArray.getJSONObject(i).getString("img")
            val nombre = jsonArray.getJSONObject(i).getString("name")
            val nivel = jsonArray.getJSONObject(i).getString("level")

            val newItem = Items(i,nombre,imagen,nivel,100)
            listaDeItems.add(newItem)
        }
        StoreMenuFragment().setRecyclerView()
    }

    val listaDeItems = ArrayList<Items>()

}