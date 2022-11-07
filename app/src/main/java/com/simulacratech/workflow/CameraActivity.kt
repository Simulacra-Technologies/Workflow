package com.simulacratech.workflow

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class CameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        imgUpload = findViewById(R.id.imgUpload)
        findViewById<Button>(R.id.btnRetake).setOnClickListener {
            checkAndRequestPermissions(this)
        }
        checkAndRequestPermissions(this)
    }

    private lateinit var imgUpload: ImageView
    private var bsPair: BitmapStringPair? = null
    private var imageUri: Uri? = null

    private fun cameraIntent() {
        requestCode = REQUEST_CAMERA
        try {
            val values = ContentValues()
            values.put(MediaStore.Images.Media.TITLE, "New Picture")
            values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera")
            imageUri = contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            resultLauncher.launch(intent)

        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Please provide write permissions from settings",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private fun checkAndRequestPermissions(context: Activity?): Boolean {
        val hasStoragePermission = ContextCompat.checkSelfPermission(context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val notGivenPermissions = ArrayList<String>()
        if (hasStoragePermission != PackageManager.PERMISSION_GRANTED) {
            notGivenPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (notGivenPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                context, notGivenPermissions.toTypedArray(),
                REQUEST_PERMISSION
            )
            return false
        }
        cameraIntent()
        return true
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_PERMISSION -> if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(applicationContext, "Attaching picture requires storage permission", Toast.LENGTH_SHORT).show()
            } else {
                cameraIntent()
            }
        }
    }
    private var requestCode: Int = 0
    var resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.e("TakePic", "onResult")
        try{
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                Log.e("TakePic2", "RESULT was OK")
                val data: Intent? = result.data
                if (requestCode == REQUEST_GALLERY)
                {
                    Log.e("TakePic3", "GALLERY matched")
                    bsPair = Utils.onSelectFromGalleryResult(
                        this@CameraActivity, data, imgUpload, imgUpload)
                }
                else if (requestCode == REQUEST_CAMERA) {
                    Log.e("TakePic4", "CAMERA matched")
                    val thumbnail = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                    bsPair = Utils.onCaptureImageResult( thumbnail, this,data, imgUpload, imgUpload)
                }
            }
        } catch (e: Exception) {
            Log.e("Image_Upload_Excep", e.toString())
        }
    }

    companion object {
        private const val REQUEST_GALLERY = 1000
        private const val REQUEST_CAMERA = 1001
        private const val REQUEST_PERMISSION = 1002

    }

}