package com.example.playlite_android.corainfo

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.get
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.example.playlite_android.R

class CoraInfoAdapter (private val lista: ArrayList<CoraInfoItem>,
                       private val context: Context)
    : RecyclerView.Adapter<CoraInfoAdapter.CoraInfoViewHolder>() {

    private lateinit var mListener: onItemClickListener
    private lateinit var onOffListener: onItemClickListener

    private var selected_cell: Int = -1

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }
    fun setOnOffItemClickListener(OnOffListener: onItemClickListener) {
        onOffListener = OnOffListener
    }

    class CoraInfoViewHolder(itemView: View,
                             listener: onItemClickListener,
                             OnOffListener: onItemClickListener)
        : RecyclerView.ViewHolder(itemView) {

        val nombre: TextView = itemView.findViewById(R.id.cora_nombre)
        val coraID: TextView = itemView.findViewById(R.id.cora_mac)
        val signal: TextView = itemView.findViewById(R.id.signal_strength)
        var switchOnOff: ImageView = itemView.findViewById(R.id.switch_button)
        var cellSelected: Boolean = itemView.isSelected

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
            switchOnOff.setOnClickListener {
                OnOffListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoraInfoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cora_recycler_cell, parent, false)

        return CoraInfoViewHolder(itemView, mListener, onOffListener)
    }

    override fun onBindViewHolder(holder: CoraInfoViewHolder, position: Int) {
        val currentItem = lista[position]

        holder.nombre.text = currentItem.nombre
        holder.coraID.text = currentItem.coraID
        holder.signal.text = currentItem.signal
        holder.cellSelected = currentItem.isSelected == true

        if(currentItem.inRangeDetected == true ) {
            holder.itemView.background.setTint(Color.WHITE)
            holder.switchOnOff.isGone = false
        } else {
            holder.itemView.background.setTint(ContextCompat.getColor(context, com.prilux.biblioteca.R.color.colorselectedcell))
            holder.switchOnOff.isGone = true
        }

    }

    fun setCellPosition(position: Int) {
        selected_cell = position
        //notifyDataSetChanged()
        notifyItemChanged(position)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun getItem(position: Int): CoraInfoItem {
        //return if (mDataSet != null) mDataSet[position] else null
        return lista.get(position)
    }

}
