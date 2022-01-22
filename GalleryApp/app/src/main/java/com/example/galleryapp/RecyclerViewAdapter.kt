package com.example.galleryapp

import android.content.Context
import android.graphics.ImageDecoder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.os.Build
import androidx.annotation.RequiresApi


class ImagesAdapter(private val dataset: List<Imagen>, context: Context): RecyclerView.Adapter<ImagesAdapter.HomeViewHolder?>(){

    private val TAG = "Adapter JM"
    private val miContext = context

    class HomeViewHolder(view: View): RecyclerView.ViewHolder(view){
        var imageView: ImageView
        val textView: TextView

        init {
            imageView = view.findViewById(R.id.picture)
            textView = view.findViewById(R.id.picture_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item,parent,false)
        return HomeViewHolder(view)
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        val source = ImageDecoder.createSource(miContext.contentResolver,dataset[position].uri)
        val miBitmap = ImageDecoder.decodeBitmap(source)
        val residedFile = ListarImagenes().resizeImage(miBitmap)

        holder.imageView.setImageBitmap(residedFile)
        holder.textView.text = dataset[position].name


        /*holder.imageView.setImageURI(dataset[position].uri)
        holder.textView.text = dataset[position].name*/
    }

    override fun getItemCount() = dataset.size

}





