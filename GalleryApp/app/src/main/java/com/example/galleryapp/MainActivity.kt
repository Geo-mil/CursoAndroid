package com.example.galleryapp

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import androidx.core.content.ContextCompat
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.OutputStream

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startcamera: ImageView = findViewById(R.id.cameraImage)
        startcamera.setOnClickListener {
            //dispatchTakePictureIntent()
            //openSomeActivityForResult()
            requestCamera()
        }

        requestRead()

        //setRecycler()

    }

    //poner en marcha el recycler view
    private fun setRecycler(listaDeImagenes: List<Imagen>){
        //referenciar al RecyclerView de activity_main.xml
        val recyclerImages = findViewById<RecyclerView>(R.id.recyclerViewImages)
        //Queremos un recycler tipo rejilla con tres columnas
        val gridLayoutManager = GridLayoutManager(this, 3)
        recyclerImages.layoutManager = gridLayoutManager
        //instanciar el adapter
        //val adapter = ImagesAdapter(ListarImagenes().listarImagenes(applicationContext))
        val adapter = ImagesAdapter(listaDeImagenes)
        //asignar el adaptador a la vista
        recyclerImages.adapter = adapter
        //actualizar el adaptador con la lista de elementos
        //refadapter.submitList(listarImagenes())
    }

/*    private val REQUEST_IMAGE_CAPTURE = 1
//
//    private fun dispatchTakePictureIntent() {
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//            takePictureIntent.resolveActivity(packageManager)?.also {
//                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE, null)
//
//
//            }
//        }
//        val cualquiera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)//.also { takePictureIntent ->
//        if(cualquiera.resolveActivity(this.packageManager)!=null){
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
//            }.launch(cualquiera)
//        }
//    }*/

    private fun getPhotoFile(fileName: String): File {
        val directoryStorage = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", directoryStorage)
    }

    // Metodo para el almacenamiento de la imagen
    private fun saveImageToStorage(imagenBitmap: Bitmap) {
        val imageOutStream: OutputStream?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues()
            values.put(MediaStore.Images.Media.DISPLAY_NAME, "image_screenshot.jpg")
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            imageOutStream = contentResolver.openOutputStream(uri!!)

            try {
                imagenBitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageOutStream)
            } finally {
                imageOutStream!!.close()
            }

        } else {
            Toast.makeText(this,"Operacion de almacenamiento no permitida", Toast.LENGTH_SHORT).show()
        }

    }

    private lateinit var filePhoto: File
    private val FILE_NAME = "photo"

    //Capturar Foto
    private fun takePhoto() {
        val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        filePhoto = getPhotoFile(FILE_NAME)


        val providerFile = FileProvider.getUriForFile(
            this,
            "com.example.galleryapp.fileprovider",
            filePhoto
        )

        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, providerFile)
        if (takePhotoIntent.resolveActivity(this.packageManager) != null) {
            observingTakeImagenActivity.launch(takePhotoIntent)
        } else {
            Toast.makeText(this,"Camera could not open", Toast.LENGTH_SHORT).show()
        }
    }

    //Observables para ver si devolvemos
    var observingTakeImagenActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK){
            val data: Intent? = result.data
            val takenPhoto = BitmapFactory.decodeFile(filePhoto.absolutePath)

            saveImageToStorage(takenPhoto)
            val resizedTakenPhoto = resizeImage(takenPhoto)
            //binding.mainImageView.setImageBitmap(resizedTakenPhoto)
        } else {
            Toast.makeText(this,"127 + ${result.resultCode} + ${result.data}", Toast.LENGTH_SHORT).show()
        }
    }

/*
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            //val data: Intent? = result.data
            val bitmap = intent?.extras?.get("data") as Bitmap
            //doSomeOperations()
            savePhotoToExternalStorage(bitmap.toString(),bitmap)
        }
    }

    fun openSomeActivityForResult() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(intent)
    }

    private fun savePhotoToExternalStorage(displayName: String, bmp: Bitmap): Boolean {
        val imageCollection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "$displayName.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.WIDTH, bmp.width)
            put(MediaStore.Images.Media.HEIGHT, bmp.height)
        }
        return try {
            contentResolver.insert(imageCollection, contentValues)?.also { uri ->
                Log.e("file path", File(uri.path.toString()).absolutePath)
                contentResolver.openOutputStream(uri).use { outputStream ->
                    if (!bmp.compress(Bitmap.CompressFormat.JPEG, 95, outputStream)) {
                        throw IOException("Couldn't save bitmap")
                    }
                }
            } ?: throw IOException("Couldn't create MediaStore entry")
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }
*/
    private val PERMISSION_CODE_TAKE_PHOTO = 2
    fun requestCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                val permissions = arrayOf(Manifest.permission.CAMERA)
                requestPermissions(permissions, PERMISSION_CODE_TAKE_PHOTO)
            } else {
                takePhoto()
            }
        } else {
            takePhoto()
        }
    }

    private val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1
    fun requestRead() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
            )
        } else {
            val listallena = ListarImagenes().listarImagenes(applicationContext)
            if(listallena!=null) {
                setRecycler(listallena)
            }
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

            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    val listallena = ListarImagenes().listarImagenes(applicationContext)
                    if(listallena!=null) {
                        setRecycler(listallena)
                    }
                    Toast.makeText(this,"Permission granted", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Permission denied", Toast.LENGTH_LONG).show()
                }
            }
            PERMISSION_CODE_TAKE_PHOTO -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    takePhoto()
                    Toast.makeText(this,"Camera Permission granted", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Camera Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



/*
lateinit var currentPhotoPath: String

@Throws(IOException::class)
private fun createImageFile(): File {
// Create an image file name
//val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
val timeStamp: String = Date().time.toString()
//val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
val storageDir: File? = getExternalStoragePublicDirectory(DIRECTORY_PICTURES)
return File.createTempFile(
"JPEG_${timeStamp}_", / * prefix * /
".jpg", / * suffix * /
storageDir / * directory * /
).apply {
// Save a file: path for use with ACTION_VIEW intents
currentPhotoPath = absolutePath
}
}
*/

}


