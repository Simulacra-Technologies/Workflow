package com.simulacratech.base.utility

import android.content.Context
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.simulacratech.base.BuildConfig
import com.simulacratech.base.R

const val DEFAULT_TAG = "IVMS - V3.0"

fun showLog(message: String?) {
    if (BuildConfig.DEBUG) {
        Log.e(DEFAULT_TAG, if (message.isNullOrEmpty()) "Empty message" else message)
    }
}

/**
 * Show Log in debug mode when [BuildConfig]
 */
fun showLog(tag: String = DEFAULT_TAG, message: String?) {
    if (BuildConfig.DEBUG) {
        Log.e(tag, if (message.isNullOrEmpty()) "Empty message" else message)
    }
}

/**
 * Log exception in debug mode
 */
fun Exception.showLog() {
    if (BuildConfig.DEBUG) {
        printFullLog(this.stackTraceToString())
    } else
        reportToCrashlytics(this)
}

private fun printFullLog(message: String) {
    if (message.length > 3000) {
        Log.e(DEFAULT_TAG, message.substring(0, 3000))
        printFullLog(message.substring(3000))
    } else {
        Log.e(DEFAULT_TAG, message)
    }
}

fun Throwable.showLog() {
    if (BuildConfig.DEBUG)
        this.printStackTrace()
    else
        reportToCrashlytics(Exception(this))
}

fun OutOfMemoryError.showLog() {
    if (BuildConfig.DEBUG)
        this.showLog()
    else
        reportToCrashlytics(Exception("OutOfMemoryError"))
}

/**
 * Avoid Kotlin's !! with common graceful error report function.
 *
 * Caveat: Using inline will make your apk side bigger as the common code gets replicated to each function.
 *
 * Sample usage:
 *
 * <code>private val someId: String by lazy { intent.getStringExtra(ID_KEY).reportNull("") }</code>
 *
 * @see <a href="https://medium.com/mobile-app-development-publication/avoid-kotlins-with-common-graceful-error-report-function-a91493459e74">Medium link</a>
 */
fun <T : Any> T?.reportNull(default: T, message: String = ""): T {
    if (this == null) {
        reportToCrashlytics(NullPointerException(message))
        return default
    }
    return this
}

/**
 * Report on Firebase crashlytics
 */
fun reportToCrashlytics(e: Exception) {
    // FirebaseCrashlytics.getInstance().recordException(this)
    e.showLog()
}

/**
 * Log contents of bundle in debug mode
 */
fun showLog(bundle: Bundle?, tag: String = DEFAULT_TAG) {
    if (BuildConfig.DEBUG)
        if (bundle != null) {
            for (key in bundle.keySet()) {
                Log.e(tag, "$key : ${bundle.get(key).toString()}")
            }
        }
}

/**
 * Iterate through hash map
 */
fun showLog(hashMap: HashMap<Int, ArrayList<String>>?, tag: String = DEFAULT_TAG) {
    if (BuildConfig.DEBUG)
        if (hashMap != null) {
            for ((k, v) in hashMap) {
                Log.e(tag, "$k = $v")
            }
        } else
            showLog("Hashmap is null", DEFAULT_TAG)
}

/**
 * Shows a short toast message on the context calling this function
 */
fun Context.showToast(
    message: String?, duration: Int = Toast.LENGTH_SHORT, gravity: Int = Gravity.CENTER
) {
    if (!message.isNullOrEmpty()) {
        val toast: Toast = Toast.makeText(this, formatToastMessage(this, message), duration)
        toast.setGravity(gravity, 0, 0)
        toast.show()
    }
}

fun formatToastMessage(context: Context, message: String): SpannableString {
    val typeface = ResourcesCompat.getFont(context, R.font.mont_regular)
    val spannableString = SpannableString(message)
    try {
        if (typeface != null) {
            spannableString.setSpan(
                StyleSpan(typeface.style), 0, message.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        spannableString.setSpan(
            RelativeSizeSpan(ResourcesCompat.getFloat(context.resources, R.dimen.toast_size)), 0,
            message.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    } catch (ex: Exception) {
        ex.showLog()
    }
    return spannableString
}

/**
 * Show alert dialog with 1 button.
 */
/*fun Context.showAlert(
    message: String?, posText: String = "Ok", onPosClick: ((Context) -> Unit)? = null
) {
    if (message != null) {
        try {
            MaterialAlertDialogBuilder(
                this, R.style.ThemeOverlay_AppCompat_Dialog_Alert
            ).apply {
                setMessage(message)
                setCancelable(false)
                setPositiveButton(posText) { dialog, _ ->
                    dialog.dismiss()
                    onPosClick?.invoke(this@showAlert)
                }.show()
            }
        } catch (ex: Exception) {
            ex.showLog()
        }
    }
}*/

/**
 * Show alert dialog with 2 buttons
 */
fun Context.showAlert(
    message: String?, posText: String = getString(android.R.string.ok),
    onPosClick: ((Context) -> Unit)? = null, negText: String? = null,
    onNegClick: ((Context) -> Unit)? = null, isCancelable: Boolean = false
) {
    if (message != null) {
        MaterialAlertDialogBuilder(this, androidx.appcompat.R.style.AlertDialog_AppCompat).apply {
            setMessage(message)
            setCancelable(isCancelable)
            setPositiveButton(posText) { dialog, _ ->
                dialog.dismiss()
                onPosClick?.invoke(this@showAlert)
            }
            setNegativeButton(negText) { dialog, _ ->
                dialog.dismiss()
                onNegClick?.invoke(this@showAlert)
            }.show()
        }
    }
}