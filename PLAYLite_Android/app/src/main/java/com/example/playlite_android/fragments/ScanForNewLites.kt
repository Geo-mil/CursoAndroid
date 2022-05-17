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
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import ble.ScanResultAdapter
import ble.libraries.ConnectionEventListener
import ble.libraries.ConnectionManager
import ble.libraries.toHexString
import com.example.playlite_android.R
import com.example.playlite_android.corainfo.CoraDataSaved
import com.example.playlite_android.corainfo.CoraInfoItem
import com.example.playlite_android.corainfo.CoraProvider
import com.example.playlite_android.corainfo.LiteActiveBleDevice
import com.example.playlite_android.viewmodel.LiteViewModel
import com.prilux.cmr.globals.DbConstants
import com.prilux.cmr.globals.parseCoraNameBleToPlatform
import org.openapitools.client.models.IOTCoraDto
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 * Use the [ScanForNewLites.newInstance] factory method to
 * create an instance of this fragment.
 */
@SuppressLint("MissingPermission")
class ScanForNewLites : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private val Etiqueta = "BLE SCANNING NEW DEVICES"

    private var device: BluetoothDevice? = null
    private var deviceGatt: BluetoothGatt? = null

    private val list = ArrayList<CoraInfoItem>()
    private val liteViewModel: LiteViewModel by viewModels()

    private var liteList = listOf<IOTCoraDto>()

    private val bluetoothAdapter: BluetoothAdapter by lazy {
        val bluetoothManager = requireActivity().getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
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

    //Extraemos el evento del adapter del item para la conexion
    private val scanResultAdapter: ScanResultAdapter by lazy {
        ScanResultAdapter(scanResults) { result ->
            if (isScanning) {
                stopBleScan()
            }
            with(result.device) {
                Log.w(Etiqueta,"Connecting to $address")
                Toast.makeText(requireContext(), (getString(com.prilux.biblioteca.R.string.conectando_a_dispositivo) + ": $address"), Toast.LENGTH_SHORT).show()

                ConnectionManager.connect(this, requireContext())

                //almacenar Cora Lite en memoria interna del teléfono
                val coraDataSaved = CoraDataSaved(result.device.name, result.device.address)
                CoraProvider().writefile(requireContext(), coraDataSaved)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_scan_for_new_lites, container, false)
        setupRecyclerView(view)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        stopBleScan()
        closeALlReferences()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ScanForNewLites.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScanForNewLites().apply {
                arguments = Bundle().apply {
                    putString("ARG_PARAM1", param1)
                    putString("ARG_PARAM2", param2)
                }
            }
    }

    private fun setupRecyclerView(view: View) {
        val recyclerId: RecyclerView = view.findViewById(R.id.scan_results_recycler_view)
        recyclerId.apply {
            adapter = scanResultAdapter
            layoutManager = LinearLayoutManager(
                requireActivity(),
                RecyclerView.VERTICAL,
                false
            )
            isNestedScrollingEnabled = false
        }

        val animator = recyclerId.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

    /*******************************************************************************
     *   Bluetooth
     *******************************************************************************/

    private fun startBleScan() {
        if (bluetoothAdapter.isEnabled) {
            scanResults.clear()
            //scanResultAdapter.notifyDataSetChanged()
            bleScanner.startScan(null, scanSettings, scanCallback)
            isScanning = true
        } else {
            promptEnableBluetooth()
        }
    }

    private fun promptEnableBluetooth() {
        if (!bluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            requestBluetooth.launch(enableBtIntent)
        }
    }

    private var requestBluetooth = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            startBleScan()
        }else{
            Toast.makeText(requireContext(),"DENEGADO -AL HABILITAR ADAPTADOR BLE", Toast.LENGTH_SHORT).show()
            requireActivity().finish()
        }
    }

    private fun stopBleScan() {
        bleScanner.stopScan(scanCallback)
        isScanning = false
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
        //Clean object BLE
        LiteActiveBleDevice.clearObject()
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
                    Log.w(Etiqueta,"${device?.address} bond state changed | $bondTransition")

                    when(bondState) {
                        BluetoothDevice.BOND_BONDED -> {
                            //TODO: conectado a un equipo, ir a pantalla de configurar y añadir
                            //navigateToNextActivity()
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

    private val scanCallback = object : ScanCallback() {
        @SuppressLint("MissingPermission")
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            val indexQuery = scanResults.indexOfFirst   { it.device.address == result.device.address }
            if (indexQuery != -1) { // A scan result already exists with the same address
                scanResults[indexQuery] = result
                scanResultAdapter.notifyItemChanged(indexQuery)
            } else {
                with(result.device) {
                    Log.i(Etiqueta, "Found BLE device! Name: ${name ?: "Unnamed"}, address: $address")
                }
                if (result.device.name != null) {
                    //Check si es modelo prlxLite - si no no chequear
                    if(result.device.name.contains(DbConstants.MODEL_LITE)) {
                        list.filter { it.coraID == result.device.address }.forEach {
                            return
                        }
                        // si no salta el return anterior , añade el dispositivo
                        scanResults.add(result)
                        scanResultAdapter.notifyItemInserted(scanResults.size - 1)
                    }
                }
            }
        }

        override fun onScanFailed(errorCode: Int) {
            Log.e(Etiqueta, "onScanFailed: code $errorCode")
        }
    }


    private val connectionEventListener by lazy {
        ConnectionEventListener().apply {

            onNotificationsEnabled = { _, _ -> }
            onCharacteristicChanged = { _, char ->
                Log.i(Etiqueta, "onCharacteristicChanged: ${char.value.toHexString() }")
            }
            onConnectionSetupComplete = { gatt ->
                stopBleScan()
                device = gatt.device
                deviceGatt = gatt
                // Eliminar el registro del listener
                ConnectionManager.unregisterListener(this)
                // Comprobar el estado del Bonding, nos registramos al receiver
                if (gatt.device.bondState != BluetoothDevice.BOND_BONDED) {
                    requireActivity().registerReceiver(broadcastReceiver, IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED))
                } else {
                    //TODO: conectado a un equipo, ir a pantalla de configurar y añadir
                    //navigateToNextActivity()
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

    fun onbackpressed(){
        activity?.onBackPressed().run {

        }
    }
}