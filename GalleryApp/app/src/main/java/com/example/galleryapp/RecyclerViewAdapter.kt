package com.example.galleryapp

import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import android.graphics.BitmapRegionDecoder
import android.graphics.Rect
import android.util.Log
import java.io.File


class ImagesAdapter(private val dataset: List<Imagen>): RecyclerView.Adapter<ImagesAdapter.HomeViewHolder?>(){
 /*   private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemclickListener(listener: onItemClickListener){
        mListener = listener
    }*/
    private val TAG = "Adapter JM"

    class HomeViewHolder(view: View/*, listener: onItemClickListener*/): RecyclerView.ViewHolder(view){
        var imageView: ImageView
        val textView: TextView

        init {
            imageView = view.findViewById(R.id.picture)
            textView = view.findViewById(R.id.picture_name)

 /*           view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }*/

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item,parent,false)
        return HomeViewHolder(view/*, mListener*/)
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        /*val bitmapFile = BitmapFactory.decodeFile(dataset[position].uri.toString())
        val residedFile = resizeImage(bitmapFile)
        Log.v(TAG, "path: ${file.absolutePath}")

        holder.imageView.setImageBitmap(residedFile)
        holder.textView.text = dataset[position].name*/


        holder.imageView.setImageURI(dataset[position].uri)
        holder.textView.text = dataset[position].name
    }

    override fun getItemCount() = dataset.size

}



//Rescalado de la imagen para ajustar a la UI
fun resizeImage(imagen: Bitmap): Bitmap {
    val resized = Bitmap.createScaledBitmap(imagen, 250, 250, true)
    return resized
}

//private fun setPic(imageView: ImageView, currentPhotoPath: String) {
//    // Get the dimensions of the View
//    val targetW: Int = imageView.width
//    val targetH: Int = imageView.height
//    Log.v("setPic", "Height: $targetH")
//    val bmOptions = BitmapFactory.Options().apply {
//        // Get the dimensions of the bitmap
//        inJustDecodeBounds = true
//
//        val photoW: Int = outWidth
//        val photoH: Int = outHeight
//        Log.v("setPic", "Picture Height: $photoH")
//        //avoid division by 0
//        var scaleFactor: Int = 1
//        if(targetH==0) {
//            if (targetW == 0) scaleFactor = 1
//            else scaleFactor = photoW / targetW
//            // Determine how much to scale down the image
//
//        }else{
//            if (targetW == 0) {
//                scaleFactor = photoH / targetH
//                Log.v("setPic", "ScaleFactor: $scaleFactor")
//            }else scaleFactor = (photoW / targetW).coerceAtMost(photoH / targetH)
//        }
//
//        // Decode the image file into a Bitmap sized to fill the View
//        inJustDecodeBounds = false
//        inSampleSize = scaleFactor
//        inPurgeable = true
//    }
//    BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.also { bitmap ->
//        imageView.setImageBitmap(bitmap)
//    }
//}


/*
class RecyclerViewAdapter(private val onClick: (Imagen) -> Unit):
    ListAdapter<Imagen, RecyclerViewAdapter.ImageViewHolder>(Imagen.DiffCallback) {


    class ImageViewHolder(view: View, onClick: (Imagen) -> Unit) : RecyclerView.ViewHolder(view){
        private val title: TextView = view.findViewById(R.id.picture_name)
        private val picture: ImageView = view.findViewById(R.id.picture)

        init {
            picture.setOnClickListener {
                val image = view.tag as? Imagen ?: return@setOnClickListener
                onClick(image)
            }
        }

        fun bind(imagen: Imagen) {
            title.text = imagen.name
            picture.setImageURI(imagen.uri)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ImageViewHolder(view, onClick)
    }

    override fun onBindViewHolder(viewHolder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        viewHolder.bind(image)
    }

}*/
