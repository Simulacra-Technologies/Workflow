package com.simulacratech.base.utility

interface PermissionListener {
    fun onPermissionUtilResult(isGranted: Boolean, isPermanentlyDenied: Boolean)
}