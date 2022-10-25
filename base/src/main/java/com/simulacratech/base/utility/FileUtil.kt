package com.simulacratech.base.utility

import android.content.Context
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.OpenableColumns
import com.google.gson.GsonBuilder
import java.io.*
import java.net.URISyntaxException

class FileUtil {
    /**
     * Write/Overwrite the [String] data passed in the given file name. This file is stored at the
     * following location in the device storage:
     * `data/data/com.aidash.ivms/files/`
     *
     * Overwrites the file of it already exists.
     */
    fun writeToStorage(context: Context, file: String, data: String): Pair<Boolean, String> {
        val fileOutputStream: FileOutputStream
        var result: Pair<Boolean, String>
        try {
            fileOutputStream = context.openFileOutput(file, Context.MODE_PRIVATE)
            fileOutputStream.write(data.toByteArray())
            val filePath = "${context.filesDir}/${file}"
            result = Pair(true, filePath)
        } catch (e: Exception) {
            e.showLog()
            result = Pair(false, e.message ?: "Error while saving file")
        }
        return result
    }

    /**
     * Write/Overwrite the [String] data passed in the given file name as json. This file is stored at the
     * following location in the device storage:
     * `data/data/com.aidash.ivms/files/`
     *
     * Overwrites the file of it already exists.
     */

    fun writeToStorage(
        context: Context,
        fileName: String,
        data: Any,
        type: String?
    ): Pair<Boolean, String> {
        val result: Pair<Boolean, String>
        return if (fileName.endsWith(".json", true)) {
            result = try {
                File("${context.filesDir}/${fileName}").writeText(
                    GsonBuilder().setPrettyPrinting().create().toJson(data)
                )
                Pair(true, "${context.filesDir}/${fileName}")
            } catch (e: Exception) {
                e.showLog()
                Pair(false, e.message ?: "Error while saving file")
            }
            result
        } else
            Pair(false, "error while saving file")
    }


    /**
     * Read and return the contents of a file in [String] format.
     *
     * Returns null if file is not available or any exception occurs.
     */
    fun readFromStorage(context: Context, fileName: String): String? {
        val fileInputStream: FileInputStream
        val dataString = StringBuilder()
        try {
            fileInputStream = context.openFileInput(fileName)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferReader = BufferedReader(inputStreamReader)
            var line: String?
            while (bufferReader.readLine().also { line = it } != null) {
                dataString.append(line)
            }
            return dataString.toString()
        } catch (e: Exception) {
            e.showLog()
        }
        return null
    }

    fun copyFileFromDeviceToAppPrivateStorage(
        context: Context, sourceUri: Uri, destinationFile: File?
    ): File? {
        val destinationFileNotNull: File = if (destinationFile == null) {
            var fileName = ""
            sourceUri.let { returnUri ->
                context.contentResolver.query(returnUri, null, null, null, null)
            }?.use { cursor ->
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                cursor.moveToFirst()
                fileName = cursor.getString(nameIndex)
            }
            File(context.filesDir, fileName)
        } else
            destinationFile

        return try {
            if (!destinationFileNotNull.exists()) {
                destinationFileNotNull.mkdirs()
                destinationFileNotNull.delete()
            }
            val inputStream: InputStream? = context.contentResolver.openInputStream(sourceUri)
            if (inputStream == null) {
                showLog("inputStream is null in copyFileFromDeviceFile()")
                return null
            }
            val outputStream: OutputStream = FileOutputStream(destinationFileNotNull)
            inputStream.copyTo(outputStream)
            inputStream.close()
            outputStream.close()
            showLog("File copied successfully at:${destinationFileNotNull.absolutePath}")
            destinationFileNotNull
        } catch (e: IOException) {
            e.showLog()
            null
        }
    }

    @Throws(URISyntaxException::class)
    fun getFilePath(context: Context, contentUri: Uri): String? {
        var uri = contentUri
        var selection: String? = null
        var selectionArgs: Array<String>? = null
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (DocumentsContract.isDocumentUri(context.applicationContext, uri)) {
            val docId = DocumentsContract.getDocumentId(uri)
            val split = docId.split(":".toRegex()).toTypedArray()
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            selection = "_id=?"
            selectionArgs = arrayOf(
                split[1]
            )
        }
        if ("content".equals(uri.scheme, ignoreCase = true)) {
            val projection = arrayOf(
                MediaStore.Images.Media.DATA
            )
            try {
                val cursor =
                    context.contentResolver.query(uri, projection, selection, selectionArgs, null)
                val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                if (cursor.moveToFirst()) {
                    val data = cursor.getString(columnIndex)
                    cursor.close()
                    return data
                }
            } catch (e: Exception) {
                e.showLog()
            }
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
        }
        return null
    }
}