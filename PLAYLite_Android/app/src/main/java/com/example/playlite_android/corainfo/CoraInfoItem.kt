package com.example.playlite_android.corainfo

import android.graphics.drawable.Drawable

data class CoraInfoItem (
    val nombre: String? = "",
    val coraID: String? = "",
    var signal: String? = "",
    val switchStateOnOff: Drawable? = null,
    var isSelected: Boolean? = false,
    var inRangeDetected: Boolean? = false,
    var timeDetected: Long? = 0
)
