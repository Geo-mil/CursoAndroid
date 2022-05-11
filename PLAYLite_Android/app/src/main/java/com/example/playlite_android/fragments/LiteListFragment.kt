package com.example.playlite_android.fragments

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.*
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ble.libraries.ConnectionEventListener
import ble.libraries.ConnectionManager
import ble.libraries.toHexString
import com.example.playlite_android.R
import com.example.playlite_android.corainfo.CoraDataSaved
import com.example.playlite_android.corainfo.CoraInfoAdapter
import com.example.playlite_android.corainfo.CoraInfoItem
import com.example.playlite_android.corainfo.CoraProvider
import com.example.playlite_android.viewmodel.LiteViewModel
import com.prilux.cmr.globals.DbConstants
import com.prilux.cmr.globals.parseCoraNameBleToPlatform
import com.prilux.cmr.globals.parseCoraNamePlatformToBle
import org.openapitools.client.models.Cora
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate
import kotlin.reflect.KProperty

@SuppressLint("MissingPermission")
class LiteListFragment : Fragment() {

    //Declaración de variables y valores
    private val TAG = "MAIN BLE FRAGMENT"

    private var device: BluetoothDevice? = null
    private var deviceGatt: BluetoothGatt? = null

    private val list = ArrayList<CoraInfoItem>()

    private lateinit var adapter : CoraInfoAdapter

    private val bluetoothAdapter: BluetoothAdapter by lazy {
        val bluetoothManager = requireActivity().getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private val bleScanner by lazy {
        bluetoothAdapter.bluetoothLeScanner
    }

    private val scanSettings = ScanSettings.Builder()
        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
        .build()

    private var isScanning = false

    private val scanResults = mutableListOf<ScanResult>()

    private val liteViewModel: LiteViewModel by viewModels()

    //Funciones propias del fragmento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG,"onCreate")

        //getCoraLite()

        val listacoralites = mutableListOf<CoraInfoItem>()
        liteViewModel.listLites(requireContext()).value?.let { listacoralites.addAll(it) }
        for (coras in listacoralites) {
            list.add(CoraInfoItem(coras.nombre, coras.coraID, "---",
                context?.let { AppCompatResources.getDrawable(it,com.prilux.biblioteca.R.drawable.bulb) }
            ))
        }

        startBleScan()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lite_list, container, false)
        setupRecyclerView(view)
        return view
    }


    override fun onDestroy() {
        super.onDestroy()
        stopBleScan()
        stopTimer()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LiteListFragment().apply {
                arguments = Bundle().apply {

                }
        }
    }

    //Funciones privadas

    private fun promptEnableBluetooth() {
        if (!bluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            requestBluetooth.launch(enableBtIntent)
        }
    }

    @SuppressLint("MissingPermission")
    private fun startBleScan() {
        if (bluetoothAdapter.isEnabled) {
            scanResults.clear()
            startTimer()
            //         scanResultAdapter.notifyDataSetChanged()
            bleScanner.startScan(null, scanSettings, scanCallback)
            isScanning = true
        } else {
            promptEnableBluetooth()
        }
    }

    private var requestBluetooth = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            startBleScan()
        }else{
            Toast.makeText(requireContext(),"Permission denied", Toast.LENGTH_SHORT).show()
            requireActivity().finish()
        }
    }

    @SuppressLint("MissingPermission")
    private fun stopBleScan() {
        bleScanner.stopScan(scanCallback)
        isScanning = false
    }

    /*******************************************
     * TIMER BLE HANDLER
     *******************************************/
    private val timerCheckBle = Timer("schedule", true)

    @SuppressLint("MissingPermission")
    private fun startTimer() {
        timerCheckBle.scheduleAtFixedRate(1000,5000){
            val getCurrentTime = System.currentTimeMillis()
            for((index, device) in list.withIndex()) {
                if(device.inRangeDetected == true) {
                    val elapsedTime = getCurrentTime - device.timeDetected!!
                    if (elapsedTime > 2000) {

                        val nameToSearch = device.coraID?.let {
                            parseCoraNamePlatformToBle(
                                it,
                                DbConstants.MODEL_LITE
                            )
                        }
                        Log.e(TAG, ("[${elapsedTime} milisec"))
                        list[index].inRangeDetected = false
                        val indexQuery = scanResults.indexOfFirst { it.device.name == nameToSearch }
                        if(indexQuery != -1) {
                            scanResults.removeAt(indexQuery)
                            adapter.notifyItemChanged(index)
                        }
                    } else {
                        list[index].timeDetected = getCurrentTime
                    }
                }
            }
        }
    }

    private fun stopTimer() {
        timerCheckBle.cancel()
    }

    private fun setupRecyclerView(view: View) {
        adapter = CoraInfoAdapter(list, requireContext())
        val recyclerId: RecyclerView? = view.findViewById(R.id.recycler_lites)
        if (recyclerId != null) {
            recyclerId.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerId.adapter = adapter
            recyclerId.setHasFixedSize(true)
        }
        adapter.setOnItemClickListener(object : CoraInfoAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                requireActivity().runOnUiThread {
                    Toast.makeText(context, ("LEIDO BLE $position"), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
        adapter.setOnOffItemClickListener(object : CoraInfoAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                requireActivity().runOnUiThread {
                    Toast.makeText(context, ("PULSADO SWITCH $position"), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    /*******************************************
     * Callback
     *******************************************/
    private val scanCallback = object : ScanCallback() {
        @SuppressLint("MissingPermission")
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            val indexQuery = scanResults.indexOfFirst { it.device.address == result.device.address }

            var nameToSearch: String

            if (indexQuery != -1) { // A scan result already exists with the same address
                scanResults[indexQuery] = result
                //scanResultAdapter.notifyItemChanged(indexQuery)
                // En este caso ya está en la lista del scan.
                // Solo debemos actualizar la variable del control de tiempo
                for((index, device) in list.withIndex()) {
                    nameToSearch = parseCoraNameBleToPlatform(
                        result.device.name,
                        DbConstants.MODEL_LITE
                    )
                    // El dispositivo descargado de plataforma sigue en rango de detección.
                    // reiniciamos el contador de tiempo
                    if (device.coraID == nameToSearch) {
                        list[index].timeDetected = System.currentTimeMillis()
                    }
                }
            } else {
                with(result.device) {
                    Log.i(TAG, "Found BLE device! Name: ${name ?: "Unnamed"}, address: $address")
                }

                if (result.device.name != null) {
                    //Check si es modelo prlxIoT- si no no chequear
                    if (result.device.name.contains(DbConstants.MODEL_LITE)) {
                        nameToSearch = parseCoraNameBleToPlatform(
                            result.device.name,
                            DbConstants.MODEL_LITE
                        )
                        // si no salta el return anterior , añade el dispositivo
                        scanResults.add(result)
                        for((index, device) in list.withIndex()) {
                            // El dispositivo descargado de plataforma esta en rango de detección.
                            if (device.coraID == nameToSearch) {
                                list[index].inRangeDetected = true
                                list[index].timeDetected = System.currentTimeMillis()
                                adapter.notifyItemChanged(index)
                            }
                        }
                    }
                }
            }
        }

        override fun onScanFailed(errorCode: Int) {
            Log.e(TAG, "onScanFailed: code $errorCode")
        }
    }

    private val connectionEventListener by lazy {
        ConnectionEventListener().apply {

            onNotificationsEnabled = { _, _ ->

            }
            onCharacteristicChanged = { _, char ->


                Log.i(TAG, "onCharacteristicChanged: ${char.value.toHexString() }")
            }
            onConnectionSetupComplete = { gatt ->
                stopBleScan()

                device = gatt.device
                deviceGatt = gatt

                // Eliminar el registro del listener
                ConnectionManager.unregisterListener(this)

                // Asiganar las Globales del BLE
                /*      CmrBleActiveDevice.coraCmr = coraCmr
                      CmrBleActiveDevice.device = gatt.device
                      CmrBleActiveDevice.deviceGatt = gatt*/

                // Comprobar el estado del Bonding, nos registramos al receiver
                if (gatt.device.bondState != BluetoothDevice.BOND_BONDED) {
                    requireActivity().registerReceiver(broadcastReceiver, IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED))
                } else {
                    navigateToNextActivity()
                }

            }

            onCharacteristicRead = { _, characteristic ->
                val leido = characteristic.value
                val string = String(leido, Charsets.UTF_8)

                requireActivity().runOnUiThread {
                    Toast.makeText(activity, ("LEIDO BLE $string"), Toast.LENGTH_SHORT).show()
                }
            }

            onDisconnect = {
                requireActivity().runOnUiThread {
                    Toast.makeText(activity,("DESCONECTADO BLE"), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            with(intent) {
                if (action == BluetoothDevice.ACTION_BOND_STATE_CHANGED) {
                    val device = getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    val previousBondState = getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, -1)
                    val bondState = getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, -1)
                    val bondTransition = "${previousBondState.toBondStateDescription()} to " +
                            bondState.toBondStateDescription()
                    Log.w(TAG,"${device?.address} bond state changed | $bondTransition")

                    when(bondState) {
                        BluetoothDevice.BOND_BONDED -> {
                            navigateToNextActivity()
                        }
                    }
                }
            }
        }

        private fun Int.toBondStateDescription() = when (this) {
            BluetoothDevice.BOND_BONDED -> "BONDED"
            BluetoothDevice.BOND_BONDING -> "BONDING"
            BluetoothDevice.BOND_NONE -> "NOT BONDED"
            else -> "ERROR: $this"
        }
    }

    private fun navigateToNextActivity() {
        // Eliminar el registro del listener
        ConnectionManager.unregisterListener(connectionEventListener)
    }

}

