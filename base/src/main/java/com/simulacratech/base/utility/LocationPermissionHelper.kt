package com.simulacratech.base.utility

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionUtils(
    private val activity: Activity,
    private val rationaleMessage: String? = "",
    private val permission: String,
    private val code: Int,
    private val permissionListener: PermissionListener
) {
    // Check permissions at runtime
    fun checkPermission() {
        val permissionResult = ContextCompat.checkSelfPermission(activity, permission)
        showLog("PERMISSION RESULT $permissionResult")
        if (permissionResult != PackageManager.PERMISSION_GRANTED) {
            if (activity.shouldShowRequestPermissionRationale(permission)) {
                showLog("RATIONALE SHOWN FROM CHECK PERMISSION")
                showRationale()
            } else {
                showLog("NOT SHOWING RATIONALE")
                makeRequest()
            }
        } else //permission granted
            permissionListener.onPermissionUtilResult(
                isGranted = true,
                isPermanentlyDenied = false
            )
    }

    private fun showRationale() {
        activity.showAlert(rationaleMessage, activity.getString(android.R.string.ok), {
            makeRequest()
        }, activity.getString(android.R.string.cancel), {
            // return result to the parent activity/fragment as Cancelled
            permissionListener.onPermissionUtilResult(
                isGranted = false,
                isPermanentlyDenied = false
            )
        })
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(activity, arrayOf(permission), code)
    }

    fun onPermissionResult(grantResults: IntArray) {
        showLog("PERMISSION RESULT RECEIVED", "PERMISSION UTILS")
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionListener.onPermissionUtilResult(isGranted = true, isPermanentlyDenied = false)
        } else
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    permission
                )
            ) {
                showLog("RATIONALE SHOWN FROM PERMISSION RESULT")
                showRationale()
            } else //permanently denied
                permissionListener.onPermissionUtilResult(
                    isGranted = false,
                    isPermanentlyDenied = true
                )
    }
}