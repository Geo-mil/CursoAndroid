package ble

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import ble.libraries.ConnectionEventListener
import ble.libraries.ConnectionManager
import com.prilux.biblioteca.R
import com.prilux.biblioteca.databinding.ActivityBleScannerBinding


class MainBleScanner : AppCompatActivity() {
    private val TAG = "MAIN BLE ACTIVITY"

    private lateinit var binding: ActivityBleScannerBinding
    companion object {
        private const val PERMISSION_BLE = 100
        private const val ENABLE_BLUETOOTH_REQUEST_CODE = 1
        private const val LOCATION_PERMISSION_REQUEST_CODE = 2
    }
    /*******************************************
     * Properties
     *******************************************/

    private val bluetoothAdapter: BluetoothAdapter by lazy {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private val bleScanner by lazy {
        bluetoothAdapter.bluetoothLeScanner
    }

    private val scanSettings = ScanSettings.Builder()
        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
        .build()

    private var isScanning = false
        set(value) {
            field = value
            runOnUiThread {
                val idButton = findViewById<TextView>(R.id.scan_button)
                idButton.text = if (value) "Stop Scan" else "Start Scan"
            }
        }

    private val scanResults = mutableListOf<ScanResult>()
    private val scanResultAdapter: ScanResultAdapter by lazy {
        ScanResultAdapter(scanResults) { result ->
            if (isScanning) {
                stopBleScan()
            }
            with(result.device) {
                Log.w(TAG,"Connecting to $address")
                ConnectionManager.connect(this, this@MainBleScanner)
            }
        }
    }

   /* private val isLocationPermissionGranted
        get() = hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)
*/
    /*******************************************
     * Activity function overrides
     *******************************************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBleScannerBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        if (findViewById<Button>(R.id.scan_button) != null) {
            val scanButtonId: TextView = findViewById(R.id.scan_button)
            scanButtonId.setOnClickListener {
                if (isScanning) {
                    stopBleScan()
                } else
                    startBleScan()
            }
            setupRecyclerView()
        }
    }

    override fun onResume() {
        super.onResume()
        verifyLocationPermissions()
        ConnectionManager.registerListener(connectionEventListener)
  /*      if (!bluetoothAdapter.isEnabled) {
            promptEnableBluetooth()
        }*/
    }



    /*******************************************
     * Private functions
     *******************************************/


    @SuppressLint("MissingPermission")
    private fun promptEnableBluetooth() {
        if (!bluetoothAdapter.isEnabled) {
            bluetoothAdapter.enable()
            /*val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, ENABLE_BLUETOOTH_REQUEST_CODE)*/
        }
    }

    @SuppressLint("MissingPermission")
    private fun startBleScan() {
        if (bluetoothAdapter.isEnabled) {
            scanResults.clear()
            scanResultAdapter.notifyDataSetChanged()
            bleScanner.startScan(null, scanSettings, scanCallback)
            isScanning = true
        } else {
            promptEnableBluetooth()
        }
    }

    private fun verifyLocationPermissions() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED ||
            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED
        ) {
            val permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            requestPermissions(permissions, LOCATION_PERMISSION_REQUEST_CODE)
        } else{
            verifyBLEApadterPermissions()
        }

    }

    private fun verifyBLEApadterPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_DENIED
            ) {
                val permissions = arrayOf(
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT
                )
                requestPermissions(permissions, ENABLE_BLUETOOTH_REQUEST_CODE)
            } else {
                verifyBLEPermissions()
            }
        } else {
            if(!bluetoothAdapter.isEnabled) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                requestBluetooth.launch(enableBtIntent)
            } else {
                verifyBLEPermissions()
            }
        }
    }

    private var requestBluetooth = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            verifyBLEPermissions()
        }else{
            Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
        }
    }
    private fun verifyBLEPermissions() {
         if (checkSelfPermission(Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_DENIED  ||
             checkSelfPermission(Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_DENIED
             /*checkSelfPermission(Manifest.permission.BLUETOOTH_PRIVILEGED) == PackageManager.PERMISSION_DENIED ||
             checkSelfPermission(Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_DENIED ||
             checkSelfPermission(Manifest.permission.BLUETOOTH_ADVERTISE) == PackageManager.PERMISSION_DENIED*/
            ) {
            val permissions = arrayOf(
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.BLUETOOTH)
                /*Manifest.permission.BLUETOOTH_PRIVILEGED,
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_ADVERTISE)*/
            requestPermissions(permissions, PERMISSION_BLE)
        } else{
            startBleScan()
        }

    }
    //Control de Permisos
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Toast.makeText(this,"$requestCode", Toast.LENGTH_SHORT).show()
        when(requestCode){
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    verifyBLEApadterPermissions()
                }else{
                    Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
            ENABLE_BLUETOOTH_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    verifyBLEPermissions()
                }else{
                    Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
            PERMISSION_BLE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startBleScan()
                }else{
                    Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
    /*private var requestBluetooth = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            //granted
        }else{
            //deny
        }
    }

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                Log.d("test006", "${it.key} = ${it.value}")
            }
        }*/
    @SuppressLint("MissingPermission")
    private fun stopBleScan() {
        bleScanner.stopScan(scanCallback)
        isScanning = false
    }

   /* private fun requestLocationPermission() {
        if (isLocationPermissionGranted) {
            return
        }

        runOnUiThread {
            val alertDialogBuilder = AlertDialog.Builder(applicationContext)
            alertDialogBuilder.setTitle("Location permission required")
            alertDialogBuilder.setMessage("Starting from Android M (6.0), the system requires apps to be granted \" +\n" +
                    "                    \"location access in order to scan for BLE devices.")
            alertDialogBuilder.setPositiveButton(R.string.btn_aceptar) { dialog, which ->
                verifyLocationPermissions()
            }
            alertDialogBuilder.show()
        }
    }*/

    private fun setupRecyclerView() {
        val recyclerId: RecyclerView = findViewById(R.id.scan_results_recycler_view)
        recyclerId.apply {
            adapter = scanResultAdapter
            layoutManager = LinearLayoutManager(
                this@MainBleScanner,
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

    /*******************************************
     * Callback bodies
     *******************************************/

    private val scanCallback = object : ScanCallback() {
        @SuppressLint("MissingPermission")
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            val indexQuery = scanResults.indexOfFirst   { it.device.address == result.device.address }
            if (indexQuery != -1) { // A scan result already exists with the same address
                scanResults[indexQuery] = result
                scanResultAdapter.notifyItemChanged(indexQuery)
            } else {
                with(result.device) {
                    Log.i(TAG, "Found BLE device! Name: ${name ?: "Unnamed"}, address: $address")
                }
                if (result.device.name != null) {
                    if (result.device.name.contains("prlxIoT")) {
                        scanResults.add(result)
                        scanResultAdapter.notifyItemInserted(scanResults.size - 1)
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
            onConnectionSetupComplete = { gatt ->
                Intent(this@MainBleScanner, BleOperationsActivity::class.java).also {
                    it.putExtra(BluetoothDevice.EXTRA_DEVICE, gatt.device)
                    startActivity(it)
                }
                ConnectionManager.unregisterListener(this)
            }
            onDisconnect = {
                runOnUiThread {
           /*         alert {
                        title = "Disconnected"
                        message = "Disconnected or unable to connect to device."
                        positiveButton("OK") {}
                    }.show()

            */
                    val alertDialogBuilder = AlertDialog.Builder(applicationContext)
                    alertDialogBuilder.setTitle("Disconnected")
                    alertDialogBuilder.setMessage("Disconnected or unable to connect to device.")
                    alertDialogBuilder.setPositiveButton(R.string.btn_aceptar ) { _, _ ->

                    }
                    alertDialogBuilder.show()
                }
            }
        }
    }

    /*******************************************
     * Extension functions
     *******************************************/

  /*  private fun Context.hasPermission(permissionType: String): Boolean {
        return ContextCompat.checkSelfPermission(this, permissionType) ==
            PackageManager.PERMISSION_GRANTED
    }

    private fun Activity.requestPermission(permission: String, requestCode: Int) {
        ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
    }*/
}
