package com.example.playlite_android.corainfo

import android.content.Context
import java.io.File

class CoraProvider {

    private val filename = "PriluxUserLites.json"

    fun writefile(context: Context, cora: CoraDataSaved) {
        val file = File(context.filesDir, filename)

        if(!file.exists()){
            file.createNewFile()
        }

        if(file.canWrite()){
            val rawdata = cora.nombre + ", " + cora.address + "\n"
            file.appendText(rawdata)
        }

    }

    private fun readfile(context: Context): String {
        val file = File(context.filesDir, filename)
        var rawdata = "" //: listof = ""

        if(file.exists()){
            if(file.canRead()){
                rawdata = file.readText()
                //rawdata = file.readLines().toString()
            }
        }

        return rawdata
    }

    fun getCoraLite(context: Context) : List<CoraInfoItem> {
        val litesRawData = readfile(context)
        val listOfLites = mutableListOf<CoraInfoItem>()
        if(litesRawData.lines().count() > 0) {
            litesRawData.lines().forEach { stringItem ->
                if(stringItem != "") {
                    val nombreCora = stringItem.split(",").component1()
                    val idCora = stringItem.split(",").component2()
                    listOfLites.add(CoraInfoItem(nombre = nombreCora, coraID = idCora))
                }
            }
        }
        return listOfLites
    }

}

