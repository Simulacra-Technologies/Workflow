package com.simulacratech.base.utility

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources

import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.simulacratech.base.R
import com.google.android.material.imageview.ShapeableImageView

fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)

fun Drawable.getColoredIcon(color: Int, mContext: Context): Drawable {
    this.colorFilter = PorterDuffColorFilter(mContext.getColorCompat(color), PorterDuff.Mode.SRC_IN)
    return this
}

fun Context.getDrawableIcon(drawable: Int) = AppCompatResources.getDrawable(this, drawable)

fun ImageView.setIconTint(mContext: Context, color: Int) =
    ImageViewCompat.setImageTintList(this, mContext.colorList(color))

fun Context.colorList(color: Int): ColorStateList {
    return ColorStateList.valueOf(ContextCompat.getColor(this, color))
}

fun Float?.getTwoDecimalValueInString(defaultValue: String): String {
    return getDecimalValueInString(defaultValue, 2)
}

fun Float?.getDecimalValueInString(defaultValue: String, digitsAfterDecimal: Int): String {
    return try {
        this?.let { value ->
            String.format("%.${digitsAfterDecimal}f", value)
        } ?: defaultValue
    } catch (ex: Exception) {
        ex.showLog()
        defaultValue
    }
}

fun Double?.getTwoDecimalValueInString(defaultValue: String): String {
    return getDecimalValueInString(defaultValue, 2)
}

fun Double?.getDecimalValueInString(defaultValue: String, digitsAfterDecimal: Int): String {
    return try {
        this?.let { value ->
            String.format("%.${digitsAfterDecimal}f", value)
        } ?: defaultValue
    } catch (ex: Exception) {
        ex.showLog()
        defaultValue
    }
}

/**
 * Extension function to create ArrayList out of String[] array
 */
fun Array<String>.getAsArrayList(): ArrayList<String> {
    val list = ArrayList<String>()
    this.forEach { list.add(it) }
    return list
}


fun ShapeableImageView.setStrokeToImageView(mContext: Context, strokeColor: Int) {
    val radius = resources.getDimension(R.dimen.dimen30)
    val appearance = shapeAppearanceModel.toBuilder().setAllCornerSizes(radius).build()
    shapeAppearanceModel = appearance
    setStrokeColorResource(mContext.getColorCompat(strokeColor))
}

/**
 * Omit any calls to the [View.setOnClickListener] in given time after previous invoke.
 * > **How it works?**
 * > Disables the view for specified duration after click to prevent double/multiple clicks.
 *
 * *Sample Usage:* `binding.submitButtonInForm.onDebouncedListener { }`
 *
 * @param [delayInClick] the time delay after which the [View] will be enabled to accept clicks.
 * @param [listener] code to execute when the [View] is clicked.
 */
inline fun View.onClickDebouncedListener(
    delayInClick: Long = 500L, crossinline listener: (View) -> Unit
) {
    val enableAgain = Runnable { isEnabled = true }
    setOnClickListener {
        if (isEnabled) {
            isEnabled = false
            postDelayed(enableAgain, delayInClick)
            listener(it)
        }
    }
}