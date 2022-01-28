package com.example.mysore.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mysore.Connection.APIStore
import com.example.mysore.Modelos.Items
import com.example.mysore.R
import kotlin.math.roundToInt

class StoreAdapter(private val dataset: List<Items>, private val context: Context):
    RecyclerView.Adapter<StoreAdapter.ViewHolder>(){

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }

    class ViewHolder(view: View, listener: OnItemClickListener): RecyclerView.ViewHolder(view){

        var itemImage:ImageView
        var nameText:TextView
        var priceText:TextView

        init {
            view.setOnClickListener {
                //listener.onItemClick(adapterPosition)
                listener.onItemClick(bindingAdapterPosition)
            }
            itemImage = view.findViewById(R.id.item_image)
            nameText = view.findViewById(R.id.item_name)
            priceText = view.findViewById(R.id.item_price)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.item_store, parent, false)
        return ViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val randomPrice = (Math.random() * 1000).roundToInt()
        val priceText = "${randomPrice}"+"€"
        val actualPrice = APIStore().listaDeItems[position].precio
        Glide.with(context).load(dataset[position].foto).into(holder.itemImage)
        holder.nameText.text = dataset[position].nombre
        if(actualPrice == 0) {
            APIStore().listaDeItems[position].precio = randomPrice
            holder.priceText.text = priceText
        }else{
            holder.priceText.text = actualPrice.toString()
        }

    }

    override fun getItemCount() = dataset.size

}