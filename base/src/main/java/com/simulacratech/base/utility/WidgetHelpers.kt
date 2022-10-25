package com.simulacratech.base.utility

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.text.*
import android.view.View
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.ImageViewCompat
import com.simulacratech.base.R
import java.util.*

/**
 * Extension method underLine for TextView.
 */
fun TextView.underLine() {
    paint.flags = paint.flags or Paint.UNDERLINE_TEXT_FLAG
    paint.isAntiAlias = true
}

fun isInputDecimalValid(inputInString: String): Boolean {
    return try {
        val valueInFloat = inputInString.split(".")
        if (valueInFloat.size > 1) {
            valueInFloat[1].length <= 2
        } else
            true
    } catch (ex: Exception) {
        ex.showLog()
        false
    }
}

/**
 * Extension method to check if String is Phone Number.
 */
fun String.isPhone(): Boolean {
    val p = "^1([34578])\\d{9}\$".toRegex()
    return matches(p)
}

fun String.toLower(): String {
    return this.lowercase(Locale.ENGLISH)
}

fun String.containsValue(value: String, ignoreCase: Boolean = true): Boolean {
    return this.contains(value, ignoreCase = ignoreCase)
}


/**
 * Extension method to check if String is Email.
 */
fun String.isEmail(): Boolean {
    val p = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)\$".toRegex()
    return matches(p)
}

fun EditText.onChange(textChanged: ((String) -> Unit)) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            textChanged.invoke(s.toString())
        }
    })
}

fun Spinner.onItemSelected(itemSelected: (data: Any?, position: Int) -> Unit) {
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {}

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            itemSelected(parent?.getItemAtPosition(position), position)
        }
    }
}

fun EditText.isEmpty(): Boolean {
    return this.text.trim().toString().isEmpty()
}

fun EditText.getInput(): String {
    return this.text.trim().toString()
}

fun TextView.getInput(): String {
    return this.text.trim().toString()
}

fun ProgressBar.animateProgress(
    property: String,
    toProgress: Int,
    onStart: ((String) -> Unit)? = null,
    onEnd: ((String) -> Unit)? = null
) {
    ObjectAnimator.ofInt(this, "progress", toProgress).apply {
        duration = 1000
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                onEnd?.invoke("")
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationStart(animation: Animator) {
                onStart?.invoke("")
            }
        })
        start()
    }
}

fun ImageView.setImageTint(mContext: Context, color: Int) =
    ImageViewCompat.setImageTintList(this, mContext.colorList(color))

fun TextView.setIconTint(mContext: Context, color: Int) {
    if (this.compoundDrawables[0] != null)
        this.compoundDrawables[0].colorFilter =
            PorterDuffColorFilter(mContext.getColorCompat(color), PorterDuff.Mode.SRC_IN)
}

fun String.makeBold(mContext: Context): Spannable {
    val fontBold = ResourcesCompat.getFont(mContext, R.font.mont_semibold)
    val spanBuilder = SpannableStringBuilder(this)
    if (fontBold != null)
        spanBuilder.setSpan(
            CustomTypeface("", fontBold),
            0,
            this.length,
            Spanned.SPAN_EXCLUSIVE_INCLUSIVE
        )
    return spanBuilder
}