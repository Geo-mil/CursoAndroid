package com.example.playlite_android.corainfo

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import org.openapitools.client.models.IOTCoraDto

object LiteActiveBleDevice {

    var coraIoT =  IOTCoraDto () //TODO: Cambiar IOTCoraDto por LiteCoraDto
    var device: BluetoothDevice? = null
    var deviceGatt: BluetoothGatt? = null

    fun clearObject () {
        coraIoT =  IOTCoraDto ()
        device = null
        deviceGatt = null
    }
}