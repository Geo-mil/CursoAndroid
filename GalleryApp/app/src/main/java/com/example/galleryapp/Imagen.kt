package com.example.galleryapp

import android.net.Uri
import androidx.recyclerview.widget.DiffUtil

data class Imagen(
    val id: Long,
    val uri: Uri,
    val name: String,
    val date: String
)
