package com.example.galleryapp

import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log

class ListarImagenes () {

    private val TAG = "ListarImagenes JM"

    fun listarImagenes(context: Context): List<Imagen>{
        val imageList = mutableListOf<Imagen>()

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED
        )
        val selection = MediaStore.Images.Media.DATE_ADDED
        val selectionArgs: Array<String>? = null//values-of-placeholder-variables
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} ASC"

        val query = context.contentResolver.query(
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL),
            projection,
            selection,
            selectionArgs,
            sortOrder
        )

        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dateColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
            while (cursor.moveToNext()) {
                // Use an ID column from the projection to get
                // a URI representing the media item itself.
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val contentUri: Uri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL),
                    id
                )
                val date = cursor.getString(dateColumn)
                imageList.add(Imagen(id, contentUri, name, date))
                Log.v(TAG, "Added image: ${Imagen(id, contentUri, name, date)}")
            }
        }

        return imageList
    }

    //Rescalado de la imagen para ajustar a la UI
    fun resizeImage(imagen: Bitmap): Bitmap {
        val imageheight = imagen.height
        val imageWidth = imagen.width
        val targetH = 360
        val targetW = 360
        var scaleFactor: Int = (imageWidth / targetW).coerceAtMost(imageheight / targetH)

        val resized = Bitmap.createScaledBitmap(imagen, imageWidth/scaleFactor,
            imageheight/scaleFactor, true)
        return resized
    }

}