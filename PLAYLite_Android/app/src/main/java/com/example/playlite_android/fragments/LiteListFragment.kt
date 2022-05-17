package com.example.playlite_android.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.*
import android.content.Context.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ble.libraries.ConnectionEventListener
import ble.libraries.ConnectionManager
import ble.libraries.toHexString
import com.example.playlite_android.R
import com.example.playlite_android.corainfo.CoraInfoAdapter
import com.example.playlite_android.corainfo.CoraInfoItem
import com.example.playlite_android.corainfo.LiteActiveBleDevice
import com.example.playlite_android.viewmodel.LiteViewModel
import com.prilux.cmr.globals.DbConstants
import com.prilux.cmr.globals.parseCoraNamePlatformToBle
import java.lang.NumberFormatException
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate


@SuppressLint("MissingPermission")
class LiteListFragment : Fragment() {

    //Declaración de variables y valores
    private val TAG = "MAIN BLE FRAGMENT"

    private var device: BluetoothDevice? = null
    private var deviceGatt: BluetoothGatt? = null

    private val list = ArrayList<CoraInfoItem>()

    private var flagLookForNewDevices: Boolean = true

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
    private var refreshInterval = 0L

    private val scanResults = mutableListOf<ScanResult>()

    private val liteViewModel: LiteViewModel by viewModels()

    //Funciones propias del fragmento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG,"onCreate")

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
            scanResults
            for((index, device) in list.withIndex()) {
                if(device.inRangeDetected == true) {
                    val elapsedTime = getCurrentTime - device.timeDetected!!
                    if (elapsedTime > 6000) {

                        val nameToSearch = device.coraID?.let {
                            parseCoraNamePlatformToBle(
                                it,
                                DbConstants.MODEL_LITE
                            )
                        }
                        Log.e(TAG, ("[${elapsedTime} milisec"))
                        list[index].inRangeDetected = false
                        val indexQuery = scanResults.indexOfFirst { it.device.name == device.nombre }
                        if(indexQuery != -1) {
                            scanResults.removeAt(indexQuery)
                            adapter.notifyItemChanged(index)
                        }
                    }
                }
            }
            if(!isScanning) startBleScan() // Si se detiene el scan relanzar
        }
    }

    private fun stopTimer() {
        timerCheckBle.cancel()
    }

    private fun setupRecyclerView(view: View) {
        adapter = CoraInfoAdapter(list, requireContext())

        val recyclerId: RecyclerView? = view.findViewById(R.id.recycler_lites)
        if (recyclerId != null) {
            recyclerId.itemAnimator = null
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

            if (indexQuery != -1) { // No es la primera vez que detectamos este dispositivo
                scanResults[indexQuery] = result
                // En este caso ya está en la lista de dispositivos registrados en el teléfono
                // Solo debemos actualizar la variable del control de tiempo y rssi
                for((index, device) in list.withIndex()) {
                    // El dispositivo descargado de plataforma sigue en rango de detección.
                    // reiniciamos el contador de tiempo
                    if (device.coraID == result.device.address) {
                        if(list[index].timeDetected != null) {
                            val sysTime = System.currentTimeMillis()
                            if ((sysTime - refreshInterval) > 1000) {
                                refreshInterval = sysTime
                                //actualizamos rssi
                                list[index].signal = result.rssi.toString()
                                //actualizamos interfaz
                                adapter.notifyItemChanged(index)
                            }
                        }
                        list[index].timeDetected = System.currentTimeMillis()
                    }
                }

            } else { // Es la primera vez que detectamos el dispositivo desde que hemos empezado a escanear
                with(result.device) {
                    Log.i(TAG, "Found BLE device! Name: ${name ?: "Unnamed"}, address: $address")
                }

                if (result.device.name != null) { // Si el dispositivo tiene nombre definido
                    //Check si es modelo prlxLITE nos interesa - si no, no
                    if (result.device.name.contains(DbConstants.MODEL_LITE)) {

                        scanResults.add(result)//añade el dispositivo a los resultados del scan

                        for((index, device) in list.withIndex()) {
                            // El dispositivo detectado, y en rango, está en la lista guardada en el teléfono
                            if (device.coraID == result.device.address) {
                                list[index].inRangeDetected = true
                                list[index].timeDetected = System.currentTimeMillis()
                                val valueInList = list[index]
                                val indexBySignal = list.indexOfFirst {
                                    try {
                                        it.signal?.toInt() ?: 100 < result.rssi
                                    }catch (error: NumberFormatException){
                                        Log.e (TAG, "ningun rssi menor")
                                        true
                                    }
                                }
                                if (indexBySignal != -1){
                                    //actualizamos rssi
                                    list[index].signal = result.rssi.toString()
                                    list.removeAt(index)
                                    //recolocamos al inicio de la lista
                                    list.add(indexBySignal, valueInList)
                                }
                                return
                            }
                        }

                        //Si llega aquí, es nodo nuevo
                        if (flagLookForNewDevices) {
                            //Para que no vayan saliendo más mensajes paro el scanner
                            flagLookForNewDevices = false
                            showDialogNewDevices()
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
                val string = String(leido)

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

    /*******************************************
     * CONTROL NUEVOS DISPOSITIVOS
     *******************************************/
    private fun showDialogNewDevices(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(getString(com.prilux.biblioteca.R.string.nuevos_dispositivos_detectados))

        // Set up the buttons
        builder.setPositiveButton(getString(com.prilux.biblioteca.R.string.btn_añadir)
        ) { _, _ -> //dialog, which ->
            //Paramos todos los procesos y eliminamos Callbacks
            closeALlReferences()
            //TODO: Ir al fragment de añadir nuevos dispositivos
            requireActivity().supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, ScanForNewLites.newInstance("",""))
                .addToBackStack(null)
                .commit()
        }
        builder.setNegativeButton(getString(com.prilux.biblioteca.R.string.btn_cancelar)
        ) { dialog, _ ->
            dialog.cancel()
            flagLookForNewDevices = false
        }

        builder.show()
    }

    private fun closeALlReferences() {
        //Unregister callbacks
        ConnectionManager.unregisterListener(connectionEventListener)
        //Cierro conexion del BLE
        if(LiteActiveBleDevice.device != null ) {
            ConnectionManager.teardownConnection(LiteActiveBleDevice.device!!)
        }

        try {
            requireActivity().unregisterReceiver(broadcastReceiver)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
        // Stop Scan
        stopBleScan()
        //Clean object BLE
        LiteActiveBleDevice.clearObject()
    }

}

