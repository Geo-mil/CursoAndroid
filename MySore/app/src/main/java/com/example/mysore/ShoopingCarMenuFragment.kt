package com.example.mysore

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mysore.Connection.APIStore
import com.example.mysore.Utils.Constants
import com.google.android.gms.common.server.converter.StringToIntConverter


class ShoopingCarMenuFragment : Fragment() {

    private lateinit var rootview: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootview = inflater.inflate(R.layout.fragment_shooping_car_menu, container, false)
        val itemId = arguments?.get(Constants.ITEM_ID_EXTRA)
        if(itemId != "") {
            val itemEnCesta = APIStore().listaDeItems[itemId.toString().toInt()]
            rootview.findViewById<TextView>(R.id.item_en_carrito).text = itemEnCesta.nombre
            rootview.findViewById<TextView>(R.id.precio_en_carrito).text =
                itemEnCesta.precio.toString()
            Glide.with(rootview).load(itemEnCesta.foto).into(rootview.findViewById(R.id.foto_item))
        }else{
            rootview.findViewById<TextView>(R.id.item_en_carrito).text = getString(R.string.no_item)
            rootview.findViewById<TextView>(R.id.precio_en_carrito).text = "0"
        }

        return rootview
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ShoopingCarMenuFragment().apply {

            }
    }
}