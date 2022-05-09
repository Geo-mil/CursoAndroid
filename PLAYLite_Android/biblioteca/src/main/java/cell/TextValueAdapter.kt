package cell

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.prilux.biblioteca.R

class TextValueAdapter (private val menuList: ArrayList<ItemTextValue>, private val context: Context) : RecyclerView.Adapter<TextValueAdapter.TextLabelViewHolder>() {


    private lateinit var mListener: onItemClickListener
    private var selected_cell: Int = -1

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class TextLabelViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.item_text)
        val value: TextView = itemView.findViewById(R.id.item_value)


        init {

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextLabelViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cell_text_value_item, parent, false)

        return TextLabelViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: TextLabelViewHolder, position: Int) {
        val currentItem = menuList[position]

        holder.text.text = currentItem.text
        holder.value.text = currentItem.value

        if (position == selected_cell) {
            holder.itemView.setBackgroundColor( ContextCompat.getColor(context, com.prilux.biblioteca.R.color.colorselectedcell))
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }

    }


    fun setCellPosition(position: Int) {
        selected_cell = position
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    fun getItem(position: Int): ItemTextValue {
        //return if (mDataSet != null) mDataSet[position] else null
        return menuList.get(position)
    }


    /* fun filter(filter: String) {
         productsList.forEach {
             if (it.categoriaId == filter ) {

             }
             //cargar todos los elementos o hacer un intent a otra pantalla
             //notifyDataSetChanged()
         }

     }*/



}