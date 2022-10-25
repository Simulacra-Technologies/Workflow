package com.simulacratech.base.utility

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.roundToInt

const val LOCATION_REQUEST_CODE = 100

const val DEFAULT_ZOOM: Double = 12.0
const val LOCATION_UPDATE_INTERVAL: Long = 20000


val Int.dpToPx: Int get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()

val Int.pxToDp: Int get() = (this / Resources.getSystem().displayMetrics.density).roundToInt()

/**
 * convert sp to pixels
 */
val Float.spToPx: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics
    )

/**
 * convert pixels to sp
 */
val Float.pxToSp: Float get() = this / Resources.getSystem().displayMetrics.scaledDensity