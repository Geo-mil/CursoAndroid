package login

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.example.playlite_android.MainActivity
//import com.example.playlite_android.MainMenuHamburguer
import com.example.playlite_android.R
import com.example.playlite_android.databinding.ActivityMainLoginBinding
import global.login.LoginGlobalCredentials
import global.login.LoginUserClass
import kotlinx.coroutines.*
import org.openapitools.client.apis.AccountApi
import org.openapitools.client.apis.TokenAuthApi
import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ServerException
import org.openapitools.client.models.AuthenticateModel
import org.openapitools.client.models.IsTenantAvailableInput
import progressbar.progressBarStatus
import java.io.IOException


class LoginMainActivity : AppCompatActivity() {


    companion object {
        private val PERMISSION_CODE_INTERNET = 1001
    }

    var newTokeAuth: TokenAuthApi = TokenAuthApi(LoginGlobalCredentials.basePathUrl)

    private var haConcedidoPermisos = false
    private val LOG_TAG = "LOGIN_DATIVE"

    private lateinit var binding: ActivityMainLoginBinding

    private lateinit var toolbar: Toolbar

    private var counter = 0

    override fun onPause() {
        super.onPause()
        //Ocultar Barra
        progressBarStatus(binding.indeterminateBar, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        toolbar = findViewById(R.id.toolbar_login)
        setSupportActionBar(toolbar)

        //Ocultar Barra
        progressBarStatus(binding.indeterminateBar, false)

        //Compruebo permisos antes de adquirir
        verificarPermisos()

        //Cargar de Shared Preferences los datos de autenticacion
        //TODO: Se puede mejorar la autenticación:
        //Shared: Con encriptacion
        //Account manager
        //.......
        LoginGlobalCredentials.user = LoginUserClass().getCurrentUser(this)

        // Si tiene el auto-remember activo
        if (LoginGlobalCredentials.user.isRememberAccess) {
            binding.rememberPassword.isChecked = LoginGlobalCredentials.user.isRememberAccess
            // Si los datos de autenticacion no están vacios
            if (LoginGlobalCredentials.user.user.isNotEmpty() &&
                LoginGlobalCredentials.user.password.isNotEmpty() &&
                LoginGlobalCredentials.user.tenant.isNotEmpty()
            ) {
                binding.email.setText(LoginGlobalCredentials.user.user)
                binding.password.setText(LoginGlobalCredentials.user.password)
                binding.tenant.setText(LoginGlobalCredentials.user.tenant)
            }
        }


        binding.email.setCompoundDrawablesRelativeWithIntrinsicBounds(com.prilux.biblioteca.R.drawable.message,0,0,0)
        binding.password.setCompoundDrawablesRelativeWithIntrinsicBounds(com.prilux.biblioteca.R.drawable.password, 0, 0, 0)
        binding.tenant.setCompoundDrawablesRelativeWithIntrinsicBounds(com.prilux.biblioteca.R.drawable.ic_baseline_home_24, 0, 0, 0)

        binding.email.addTextChangedListener(loginTextWatcher)
        binding.password.addTextChangedListener(loginTextWatcher)
        binding.tenant.addTextChangedListener(loginTextWatcher)

        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                // Asigno el texto al modelo USER
                LoginGlobalCredentials.user.user = s.toString()
                if (s.isNotEmpty()) {
                    var drawable = AppCompatResources.getDrawable(
                        applicationContext,
                        com.prilux.biblioteca.R.drawable.message
                    )//resources.getDrawable(R.drawable.message) //Your drawable image
                    drawable = DrawableCompat.wrap(drawable!!)
                    DrawableCompat.setTint(
                        drawable, ContextCompat.getColor(
                            applicationContext,
                            com.prilux.biblioteca.R.color.colordarkblue
                        )
                    ) // Set whatever color you want
                    DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
                    binding.email.setCompoundDrawablesWithIntrinsicBounds(
                        drawable,
                        null,
                        null,
                        null
                    )
                    binding.email.setCompoundDrawablesWithIntrinsicBounds(
                        AppCompatResources.getDrawable(applicationContext, com.prilux.biblioteca.R.drawable.message),
                        null,
                        AppCompatResources.getDrawable(applicationContext, com.prilux.biblioteca.R.drawable.cancel),
                        null
                    )
                    //binding.email.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.drawable.message), null, applicationContext.getDrawable(R.drawable.cancel), null)
                } else if (s.isEmpty()) {
                    binding.email.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        com.prilux.biblioteca.R.drawable.message,
                        0, 0, 0
                    )
                    var drawable = AppCompatResources.getDrawable(
                        applicationContext,
                        com.prilux.biblioteca.R.drawable.message
                    )//applicationContext.getDrawable(R.drawable.message) //Your drawable image
                    drawable = DrawableCompat.wrap(drawable!!)
                    DrawableCompat.setTint(
                        drawable, ContextCompat.getColor(
                            applicationContext,
                            com.prilux.biblioteca.R.color.colorDefault
                        )
                    ) // Set whatever color you want
                    // DrawableCompat.setTint(drawable, resources.getColor(R.color.colorDefault)) // Set whatever color you want
                    DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
                    binding.email.setCompoundDrawablesWithIntrinsicBounds(
                        drawable,
                        null,
                        null,
                        null
                    )
                    binding.email.setCompoundDrawablesWithIntrinsicBounds(
                        AppCompatResources.getDrawable(applicationContext, com.prilux.biblioteca.R.drawable.message),
                        null, null, null
                    )
                }
            }
        })

        binding.password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                // Asigno el texto al modelo USER
                LoginGlobalCredentials.user.password = s.toString()
                if (s.isNotEmpty()) {
                    var drawable = AppCompatResources.getDrawable(
                        applicationContext,
                        com.prilux.biblioteca.R.drawable.password
                    )// resources.getDrawable(R.drawable.password) //Your drawable image
                    drawable = DrawableCompat.wrap(drawable!!)
                    DrawableCompat.setTint(
                        drawable!!,
                        getColor(com.prilux.biblioteca.R.color.colordarkblue)
                    )//resources.getColor(R.color.colordarkblue)) // Set whatever color you want
                    DrawableCompat.setTintMode(drawable!!, PorterDuff.Mode.SRC_IN)
                    binding.password.setCompoundDrawablesWithIntrinsicBounds(
                        drawable,
                        null,
                        null,
                        null
                    )
                    binding.password.setCompoundDrawablesWithIntrinsicBounds(
                        AppCompatResources.getDrawable(
                            applicationContext,
                            com.prilux.biblioteca.R.drawable.password
                        ),
                        null,
                        AppCompatResources.getDrawable(applicationContext, com.prilux.biblioteca.R.drawable.cancel),
                        null
                    )
                    //binding.password.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.drawable.password), null, resources.getDrawable(R.drawable.cancel), null)
                } else if (s.isEmpty()) {
                    binding.password.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        com.prilux.biblioteca.R.drawable.password,
                        0, 0, 0
                    )
                    var drawable = AppCompatResources.getDrawable(
                        applicationContext,
                        com.prilux.biblioteca.R.drawable.password
                    )// getDrawable(R.drawable.password) //Your drawable image
                    drawable = DrawableCompat.wrap(drawable!!)
                    DrawableCompat.setTint(
                        drawable!!,
                        getColor(com.prilux.biblioteca.R.color.colorDefault)
                    ) //resources.getColor(R.color.colorDefault)) // Set whatever color you want
                    DrawableCompat.setTintMode(drawable!!, PorterDuff.Mode.SRC_IN)
                    binding.password.setCompoundDrawablesWithIntrinsicBounds(
                        drawable,
                        null,
                        null,
                        null
                    )
                    binding.password.setCompoundDrawablesWithIntrinsicBounds(
                        AppCompatResources.getDrawable(
                            applicationContext,
                            com.prilux.biblioteca.R.drawable.password
                        ), null, null, null
                    )
                }
            }
        })

        binding.tenant.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                // Asigno el texto al modelo USER
                LoginGlobalCredentials.user.tenant = s.toString()
                if (s.isNotEmpty()) {
                    var drawable = AppCompatResources.getDrawable(
                        applicationContext,
                        com.prilux.biblioteca.R.drawable.ic_baseline_home_24
                    )// resources.getDrawable(R.drawable.password) //Your drawable image
                    drawable = DrawableCompat.wrap(drawable!!)
                    DrawableCompat.setTint(
                        drawable!!,
                        getColor(com.prilux.biblioteca.R.color.colordarkblue)
                    )//resources.getColor(R.color.colordarkblue)) // Set whatever color you want
                    DrawableCompat.setTintMode(drawable!!, PorterDuff.Mode.SRC_IN)
                    binding.tenant.setCompoundDrawablesWithIntrinsicBounds(
                        drawable,
                        null,
                        null,
                        null
                    )
                    binding.tenant.setCompoundDrawablesWithIntrinsicBounds(
                        AppCompatResources.getDrawable(
                            applicationContext,
                            com.prilux.biblioteca.R.drawable.ic_baseline_home_24
                        ),
                        null,
                        AppCompatResources.getDrawable(applicationContext, com.prilux.biblioteca.R.drawable.cancel),
                        null
                    )
                    //binding.password.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.drawable.password), null, resources.getDrawable(R.drawable.cancel), null)
                } else if (s.isEmpty()) {
                    binding.tenant.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        com.prilux.biblioteca.R.drawable.ic_baseline_home_24,
                        0, 0, 0
                    )
                    var drawable = AppCompatResources.getDrawable(
                        applicationContext,
                        com.prilux.biblioteca.R.drawable.ic_baseline_home_24
                    )// getDrawable(R.drawable.password) //Your drawable image
                    drawable = DrawableCompat.wrap(drawable!!)
                    DrawableCompat.setTint(
                        drawable!!,
                        getColor(com.prilux.biblioteca.R.color.colorDefault)
                    ) //resources.getColor(R.color.colorDefault)) // Set whatever color you want
                    DrawableCompat.setTintMode(drawable!!, PorterDuff.Mode.SRC_IN)
                    binding.tenant.setCompoundDrawablesWithIntrinsicBounds(
                        drawable,
                        null,
                        null,
                        null
                    )
                    binding.tenant.setCompoundDrawablesWithIntrinsicBounds(
                        AppCompatResources.getDrawable(
                            applicationContext,
                            com.prilux.biblioteca.R.drawable.ic_baseline_home_24
                        ), null, null, null
                    )

                }
            }
        })

        binding.developerModeImage.setOnClickListener {
            val inputEditTextField = EditText(this)
            val dialog = AlertDialog.Builder(this)
                .setTitle(com.prilux.biblioteca.R.string.advertencia)
                .setMessage(com.prilux.biblioteca.R.string.enable_developer_mode)
                .setView(inputEditTextField)
                .setPositiveButton(com.prilux.biblioteca.R.string.btn_aceptar) { _, _ ->
                    val editTextInput = inputEditTextField .text.toString()
                    if (editTextInput == "12345678") {
                        LoginGlobalCredentials.basePathUrl = LoginGlobalCredentials.basePathURLDativeDesarrollo
                        Toast.makeText(this,com.prilux.biblioteca.R.string.proceso_correcto, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,com.prilux.biblioteca.R.string.proceso_fail, Toast.LENGTH_SHORT).show()
                    }

                }
                .setNegativeButton(com.prilux.biblioteca.R.string.btn_cancelar, null)
                .create()
            dialog.show()
        }


        binding.email.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event?.action == MotionEvent.ACTION_DOWN) {
                    if (binding.email.compoundDrawables[2] != null)
                    // if (binding.email.getCompoundDrawables().get(2) != null)
                    {
                        if (event.x >= binding.email.right - binding.email.left -
                            binding.email.compoundDrawables[2].bounds.width()
                        ) {
                            if (binding.email.text.toString() != "") {
                                binding.email.setText("")
                            }
                        }
                    }
                }

                return v?.onTouchEvent(event) ?: true
            }
        })
        binding.password.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event?.action == MotionEvent.ACTION_DOWN) {
                    if (binding.password.compoundDrawables[2] != null) {
                        if (event.x >= binding.password.right - binding.password.left -
                            binding.password.compoundDrawables[2].bounds.width()
                        ) {
                            if (binding.password.text.toString() != "") {
                                binding.password.setText("")
                            }
                        }
                    }
                }
                return v?.onTouchEvent(event) ?: true
            }
        })

        binding.tenant.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event?.action == MotionEvent.ACTION_DOWN) {
                    if (binding.tenant.compoundDrawables[2] != null) {
                        if (event.x >= binding.tenant.right - binding.tenant.left -
                            binding.tenant.compoundDrawables[2].bounds.width()
                        ) {
                            if (binding.tenant.text.toString() != "") {
                                binding.tenant.setText("")
                            }
                        }
                    }
                }
                return v?.onTouchEvent(event) ?: true
            }
        })

        binding.rememberPassword.setOnClickListener {

            if (!(binding.rememberPassword.isSelected)) {
                binding.rememberPassword.isChecked = true
                binding.rememberPassword.isSelected = true
            } else {
                binding.rememberPassword.isChecked = false
                binding.rememberPassword.isSelected = false
            }
            //para asignar al auto almacenado de las credenciales
            LoginGlobalCredentials.user.isRememberAccess = binding.rememberPassword.isChecked
        }

        binding.loginButton.setOnClickListener {
            if (LoginGlobalCredentials.user.user.isNotEmpty() &&
                LoginGlobalCredentials.user.password.isNotEmpty() &&
                LoginGlobalCredentials.user.tenant.isNotEmpty()
            ) {
                if (haConcedidoPermisos) {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Por favor espere!!!" +
                                "Realizando conexion Cloud." +
                                "El proceso puede tardar", Toast.LENGTH_LONG)
                            .show()
                    }
                    startTimeCounter()
                // authProcessBLOCKING()
                } else {
                    //TODO: MEter MENSAJE permisos
                }
            } else {
                //Mostrar mensajes Campos Vacios
                val dialogBuilder = AlertDialog.Builder(this)

                // set message of alert dialog
                dialogBuilder.setMessage(com.prilux.biblioteca.R.string.campos_vacios)
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton(
                        getString(com.prilux.biblioteca.R.string.btn_aceptar),
                        { dialog, id ->

                        })
                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle(com.prilux.biblioteca.R.string.advertencia)
                // show alert dialog
                alert.show()
            }
        }

        binding.requestAccess.setOnClickListener {
            Toast.makeText(applicationContext , "NO IMPLEMENTADO", Toast.LENGTH_LONG).show()
            /*
            runBlocking {
                val job: Job = launch(context = Dispatchers.Default) {

                    try {
                        val cora = CoraApi(LoginGlobalCredentials.basePathUrl)
                        var coraList = cora.apiServicesAppCoraGetCloudCorasGet()
                        Log.e(LOG_TAG, ("$coraList"))


                    } catch (e: ServerException) {
                        Log.e(LOG_TAG, ("[${e.statusCode} -> ${e.response.toString()}"))
                    } catch (e: ClientException) {
                        Log.e(LOG_TAG, ("[${e.statusCode} -> ${e.response.toString()}"))
                    } catch (e: IOException) {
                        Log.e(LOG_TAG, ("[${e.message.toString()}"))
                    }
                }
                job.join()
            }*/
        }
    }
    private fun startTimeCounter() {
        progressBarStatus(binding.indeterminateBar, true)
        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
               // countTime.text = counter.toString()
                counter++
            }
            override fun onFinish() {
                authProcessBLOCKING()
               // countTime.text = "Finished"
            }
        }.start()
    }

    private val loginTextWatcher: TextWatcher = object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int)
        {


        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int)
        {


        }

        override fun afterTextChanged(s: Editable)
        {
            val mUsername: String = binding.email.text.toString().trim()
            val mPassword: String = binding.password.text.toString().trim()
            val mTenant: String = binding.tenant.text.toString().trim()
            val t = mUsername.isNotEmpty() && mPassword.isNotEmpty() && mTenant.isNotEmpty()
            if (t)
            {
                binding.loginButton.setBackgroundResource(com.prilux.biblioteca.R.color.white)
            }
            else
            {
                binding.loginButton.setBackgroundResource(com.prilux.biblioteca.R.color.bluemediumcolorprilux)
            }

        }
    }

    override fun onStart()
    {
        super.onStart()
        val mUsername: String = binding.email.text.toString().trim()
        val mPassword: String = binding.password.text.toString().trim()
        val t = mUsername.isNotEmpty() && mPassword.isNotEmpty()
        if (t)
        {
            binding.loginButton.setBackgroundResource(com.prilux.biblioteca.R.color.white)
        }
        else
        {
            binding.loginButton.setBackgroundResource(com.prilux.biblioteca.R.color.bluemediumcolorprilux)
        }
    }


    private fun authProcessBLOCKING() {

        //Inicializar estas variables cada vez que se autentique
        newTokeAuth = TokenAuthApi(LoginGlobalCredentials.basePathUrl)
        LoginGlobalCredentials.tokenAuth = ""
        LoginGlobalCredentials.tenantID = ""

        var errorHandler: LoginErrorHandler

        // 1 - Añadimos los headers
        addHeaders()

        // 2 - Adquirimos el Identificador del Tenant
        errorHandler = getTenantIdBLOCKING()

        // Comprobación del proceso 2- Tenant
        if (errorHandler.success == true) {
            // 3 - Generamos el token de autenticación
            //mensajeAuth, errorCode, success =
                errorHandler  = authenticate()
            //Ocultar Barra
            progressBarStatus(binding.indeterminateBar, false)
            if (errorHandler.success == true) {

                if(LoginGlobalCredentials.user.isRememberAccess) {
                    LoginUserClass().setCurrentUser(this, LoginGlobalCredentials.user)
                }else {
                    LoginUserClass().clearUser(this)
                }

                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)

            // Error en en la authentication
            } else {
                //Ocultar Barra
                progressBarStatus(binding.indeterminateBar, false)
                if (errorHandler.message?.isEmpty() == false) {
                    showAlert(errorHandler.message!!)
                }
            }
        } else {
            // Error en la adquisicion del Tenant
            if (errorHandler.message != null) {
                showAlert(errorHandler.message!!)
            }
        }
    }


    private fun addHeaders() {
        ApiClient.builder.addInterceptor { chain ->
            val original = chain.request()
            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .header(
                    "Abp-TenantId",
                    LoginGlobalCredentials.tenantID //accountOutput.tenantId.toString()
                ) // <-- this is the important lin
                .header("Authorization", "Bearer ${LoginGlobalCredentials.tokenAuth}")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    private fun getTenantIdBLOCKING(): LoginErrorHandler {
        var error = LoginErrorHandler()

        runBlocking {
            val job: Job = launch(context = Dispatchers.Default) {
                Log.e(LOG_TAG,("[${Thread.currentThread().name}] Launched coroutine getTenantIdBLOCKING"))
                val account = AccountApi(LoginGlobalCredentials.basePathUrl)
                try {
                    val accountOutput = account.apiServicesAppAccountIsTenantAvailablePost(IsTenantAvailableInput(
                        LoginGlobalCredentials.user.tenant))
                    LoginGlobalCredentials.tenantID = accountOutput.tenantId.toString()
                    error.success = true
                    error.errorCode = 0
                } catch (e: ServerException) {
                    Log.e(LOG_TAG,("[${e.statusCode} -> ${e.response.toString()}"))
                    error.success =  false
                    error.message = "${e.statusCode} -> ${e.response.toString()}"
                    error.errorCode = e.statusCode
                } catch (e: ClientException) {
                    Log.e(LOG_TAG,("[${e.statusCode} -> ${e.response.toString()}"))
                    error.success =  false
                    error.message = "${e.statusCode} -> ${e.response.toString()}"
                    error.errorCode = e.statusCode
                } catch (e: IOException) {
                    Log.e(LOG_TAG,("[${e.message.toString()}"))
                    error.success = false
                    error.message = e.message.toString()
                }
                Log.e(LOG_TAG,("[${Thread.currentThread().name}] Finished coroutine getTenantIdBLOCKING"))
            }
            job.join()
        }
        return error
    }


    private fun authenticate(): LoginErrorHandler {
        var error = LoginErrorHandler()

        runBlocking {
            val job: Job = launch(context = Dispatchers.Default) {
                val authModel =
                    AuthenticateModel(LoginGlobalCredentials.user.user, LoginGlobalCredentials.user.password, true)
                Log.e(LOG_TAG, ("[${Thread.currentThread().name}] Launched coroutine authenticate"))
                try {
                    LoginGlobalCredentials.tokenAuth = newTokeAuth.apiTokenAuthAuthenticatePost(authModel).accessToken.toString()
                    error.success = true
                    error.errorCode = 0
                } catch (e: ServerException) {
                    Log.e(LOG_TAG,("[${e.statusCode} -> ${e.response.toString()}"))
                    error.success =  false
                    error.message = "${e.statusCode} -> ${e.response.toString()}"
                    error.errorCode = e.statusCode
                } catch (e: ClientException) {
                    Log.e(LOG_TAG,("[${e.statusCode} -> ${e.response.toString()}"))
                    error.success =  false
                    error.message = "${e.statusCode} -> ${e.response.toString()}"
                    error.errorCode = e.statusCode
                } catch (e: IOException) {
                    Log.e(LOG_TAG,("[${e.message.toString()}"))
                    error.success = false
                    error.message = e.message.toString()
                }
            }
            job.join()
        }
        return error
    }

    private fun showAlert(mensaje: String) {
        //Mostrar mensajes Campos Vacios
        val dialogBuilder = AlertDialog.Builder(this)

        // set message of alert dialog
        dialogBuilder.setMessage(mensaje)
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton(
                getString(com.prilux.biblioteca.R.string.btn_aceptar),
                { dialog, id ->

                })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle(getString(com.prilux.biblioteca.R.string.proceso_fail))
        // show alert dialog
        alert.show()
    }

    /***************************************************
     * COMPROBACION DE PERMISOS GLOBALES
     **************************************************/

    private val LOCATION_PERMISSION_REQUEST_CODE = 2
    private val PERMISSION_BLE = 100

    private val bluetoothAdapter: BluetoothAdapter by lazy {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

//    private fun verificarPermisos() {
//        if (checkSelfPermission(android.Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED) {
//            val permissions = arrayOf(android.Manifest.permission.INTERNET)
//            requestPermissions(permissions, PERMISSION_CODE_INTERNET)
//        } else{
//            haConcedidoPermisos = true
//        }
//
//    }

    private fun verificarPermisos() {
        if (checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED) {
            val permissions = arrayOf(Manifest.permission.INTERNET)
            requestPermissions(permissions, PERMISSION_CODE_INTERNET)
        } else{
            verifyLocationPermissions()
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
            verifyBLEPermissions()
        }

    }

    private fun verifyBLEPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (checkSelfPermission(Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(Manifest.permission.BLUETOOTH_ADVERTISE) == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED
            ) {
                val permissions = arrayOf(
                    Manifest.permission.BLUETOOTH_ADVERTISE,
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT
                )
                requestPermissions(permissions, PERMISSION_BLE)
            } else {
                promptEnableBluetooth()
                haConcedidoPermisos = true
            }
        } else {
            if (checkSelfPermission(Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_DENIED) {
                val permissions = arrayOf(Manifest.permission.BLUETOOTH)
                requestPermissions(permissions, PERMISSION_BLE)
            } else {
                promptEnableBluetooth()
                haConcedidoPermisos = true
            }
        }

    }

    private fun promptEnableBluetooth() {
        if (!bluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            requestBluetooth.launch(enableBtIntent)
        }
    }

    //Control de Permisos
    private var requestBluetooth = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            verifyBLEPermissions()
        }else{
            Toast.makeText(this,"DENEGADO -AL HABILITAR ADAPTADOR BLE", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Toast.makeText(this,"$requestCode", Toast.LENGTH_SHORT).show()
        when(requestCode){

            PERMISSION_CODE_INTERNET -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    haConcedidoPermisos = true
                    verifyLocationPermissions()
                }else{
                    Toast.makeText(this,"Permission denied - INTERNET", Toast.LENGTH_SHORT).show()
                }
            }
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    verifyBLEPermissions()
                }else{
                    Toast.makeText(this,"Permission denied - LOCATION", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            PERMISSION_BLE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    promptEnableBluetooth()
                }else{
                    Toast.makeText(this,"Permission denied - BLE", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

        }
    }

}