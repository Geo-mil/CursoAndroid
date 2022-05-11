package com.example.playlite_android.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.playlite_android.corainfo.CoraInfoItem
import com.example.playlite_android.corainfo.CoraProvider

class LiteViewModel : ViewModel() {

    private var liteModel = MutableLiveData<List<CoraInfoItem>>()

    fun listLites(context: Context): MutableLiveData<List<CoraInfoItem>> {
        val currentList = CoraProvider().getCoraLite(context)
        liteModel.value = currentList
        return liteModel
    }

}