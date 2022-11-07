package com.simulacratech.workflow

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

object Utils {
    private const val MAX_MB_SIZE = 2f
    private const val MAX_MB_SIZE_TEXT = "2 MB"
    internal fun onSelectFromGalleryResult(
        activity: Activity,
        data: Intent?,
        imgAttach: ImageView,
        imgPreview: ImageView
    ):BitmapStringPair? {
        //imgattached = false
        Log.e("UIUtils -> onSelectGallery()", "GALLERY matched")
        val imgModel = BitmapStringPair()
        // imgModel.currentPhotoPath = null
        var factor = 100
        if (data != null) {
            try {
                imgModel.bitmap = MediaStore.Images.Media.getBitmap(activity.applicationContext.contentResolver, data.data)
                Log.e("onGallery", "" + factor)
                Log.e("onGallery20", "" + imgModel.bitmap?.width + "," + imgModel.bitmap?.height)
                val bytes = ByteArrayOutputStream()
                factor = getCompressFactor2(imgModel.bitmap!!, bytes)
                if (factor < 0) {
                    imgModel.bitmap = null
                    showDialog( activity, false, "Image is large",
                        "Please choose an image file of size ${MAX_MB_SIZE_TEXT} or less!")
                    return null
                }
                Log.e("onGallery30", "" + factor)

                var destination: File = getStorageFolder(activity)!!
                if (!destination.exists()) destination.mkdirs()
                val ss: String = getStorageFolder(activity)!!.absolutePath
                destination = File(ss, System.currentTimeMillis().toString() + ".jpg")
                val fo: FileOutputStream
                try {
                    val bb = destination.createNewFile()
                    fo = FileOutputStream(destination)
                    fo.write(bytes.toByteArray())
                    fo.close()
                    if (bb) imgModel.filePath = destination.absolutePath
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(activity, "Error creating file!", Toast.LENGTH_LONG).show()
                    return imgModel
                }
                imgAttach.visibility = View.GONE
                imgPreview.visibility = View.VISIBLE
                imgPreview.setImageBitmap(imgModel.bitmap)
                Log.e("some_attach", "filePath:${imgModel.filePath}, bitmap:${imgModel.bitmap}")
                return imgModel
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(activity, "Error attaching image!", Toast.LENGTH_LONG).show()
            }
        } else Toast.makeText(activity, "No image attached!", Toast.LENGTH_LONG).show()

        return null
    }
    fun showDialog(
        activity: Activity?,
        cancelable: Boolean,
        title: String?,
        message: String?
    ) {
        Log.e("show dialog error", message!!)
        try {
            AlertDialog.Builder(activity!!)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton("OK") { dialog, id -> }
                .show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun getCompressFactor2(bitmap: Bitmap, bytes: ByteArrayOutputStream): Int {
        var factor = 100
        val bytes2 = ByteArrayOutputStream()
        var ab = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes2)
        val bytearray2 = bytes2.toByteArray()

        //
        var mBytess = bytearray2.size / (1024f * 1024f)
        Log.e("getCompressFactor2", "" + mBytess)
        if (mBytess > 0.5) {
            factor = (0.5 * 100 / mBytess).toInt()
        }
        Log.e("getCompressFactor2_2", "$factor,$ab,$mBytess")
        ab = bitmap.compress(Bitmap.CompressFormat.JPEG, factor, bytes)
        val bytearray = bytes.toByteArray()
        mBytess = bytearray.size / (1024f * 1024f)
        Log.e("getCompressFactor2_3", "$factor,$ab,$mBytess")
        return if (mBytess > MAX_MB_SIZE) -999 else factor
    }
    private fun getStorageFolder(ac: Activity): File? {
        return ac.getExternalFilesDir(null)
    }

    internal fun onCaptureImageResult(
        thumbnail: Bitmap?,
        activity: Activity,
        data: Intent?,
        imgAttach: ImageView,
        imgPreview: ImageView
    ):BitmapStringPair? {
        val imgModel = BitmapStringPair()
        imgModel.filePath = null
        imgModel.bitmap = thumbnail

        val bytes = ByteArrayOutputStream()
        val factor: Int = getCompressFactor2(thumbnail!!, bytes)
        if (factor < 0) {
            imgModel.bitmap = null
            showDialog(activity, false, "Image is large",
                "Image size is more than " + MAX_MB_SIZE_TEXT + "! Please attach image of lower size")
            return null
        }
        Log.e("insideonCaptureImageResult", "" + factor)
        Log.e(
            "insideonCaptureImageResult2",
            "" + imgModel.bitmap?.getWidth() + "," + imgModel.bitmap?.getHeight()
        )


        var destination: File = getStorageFolder(activity)!!
        if (!destination.exists()) destination.mkdirs()

        val ss: String = getStorageFolder(activity)!!.absolutePath


        destination = File(ss, System.currentTimeMillis().toString() + ".jpg")
        val fo: FileOutputStream
        try {
            val bb = destination.createNewFile()
            fo = FileOutputStream(destination)
            fo.write(bytes.toByteArray())
            fo.close()
            if (bb) imgModel.filePath = destination.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(activity, "Error creating file!", Toast.LENGTH_LONG).show()
            return null
        }

        imgPreview.visibility = View.VISIBLE
        imgPreview.setImageBitmap(imgModel.bitmap)
        Log.e("onacti1", "nothing")
        var dialog: ProgressDialog? = null
        Thread {
            activity.runOnUiThread(Runnable {
                dialog = showProgressDialogText(activity, "Attaching image...")
            })
            Log.e("imagelengthvalue4444", "less")
            activity.runOnUiThread(Runnable { dialog?.dismiss() })
        }.start()
        Log.e("onacti", "11") // + descimage);

        //findViewById<View>(R.id.imgCross0).setVisibility(View.VISIBLE)
        // setOnClickRemoveImage()
        //imgattached = true
        return imgModel
    }
    private fun showProgressDialogText(ac: Activity, text: String?): ProgressDialog? {
        val progressDialog = ProgressDialog.show(ac, null, null)
        val layoutInflater = ac.layoutInflater
        if (text == null)
            progressDialog.setMessage("Attaching image...")
        else
            progressDialog.setMessage(text)
        progressDialog.setCancelable(false)
        return progressDialog
    }
}