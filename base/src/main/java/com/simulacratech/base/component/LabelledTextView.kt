package com.simulacratech.base.component

import android.content.Context
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.simulacratech.base.R
import com.simulacratech.base.utility.getColorCompat
import com.simulacratech.base.utility.getTwoDecimalValueInString
import com.simulacratech.base.utility.toLower


class LabelledTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    private var tv_label: AppCompatTextView
    private var tv_value: AppCompatTextView

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.labelled_textview, this, true)

        tv_label = findViewById(R.id.tv_labelled_textview_label)
        tv_value = findViewById(R.id.tv_labelled_textview_text)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it,
                R.styleable.LabelledTextView, 0, 0
            )
            val drawStart = typedArray.getResourceId(R.styleable.LabelledTextView_drawableStart, -1)
            val value = typedArray.getString(R.styleable.LabelledTextView_value)
            val isValueTextBold =
                typedArray.getBoolean(R.styleable.LabelledTextView_isValueTextBold, false)
            val isValueTextUnderlined =
                typedArray.getBoolean(R.styleable.LabelledTextView_isValueUnderlined, false)
            val valueTextColor = typedArray.getColor(
                R.styleable.LabelledTextView_valueColor,
                context.getColorCompat(R.color.colorTextPrimary)
            )
            val backgroundId = typedArray.getResourceId(R.styleable.LabelledTextView_background, -1)
            val label = typedArray.getString(R.styleable.LabelledTextView_label)
            val drawableId = typedArray.getResourceId(R.styleable.LabelledTextView_drawableEnd, -1)
            if (drawableId >= 0) {
                tv_value.compoundDrawablePadding =
                    context.resources.getDimensionPixelOffset(R.dimen.dimenDrawablePadding)
                tv_value.setCompoundDrawablesWithIntrinsicBounds(
                    null, null, AppCompatResources.getDrawable(context, drawableId), null
                )
            }


            setDrawable(drawStart)

            tv_value.text = value
            tv_label.text = label
            if (backgroundId >= 0)
                tv_value.setBackgroundResource(backgroundId)
            setValueTextBold(isValueTextBold)
            setValueWithUnderline(value, isValueTextUnderlined)
            setValueTextColor(valueTextColor)

            typedArray.recycle()
        }
    }

    fun setValueTextBold(isValueTextBold: Boolean) {
        tv_value.typeface = ResourcesCompat.getFont(
            context, if (isValueTextBold) R.font.mont_semibold else R.font.mont_regular
        )
    }

    /*fun setValueUnderlined(isValueTextUnderlined: Boolean = false) {
        if (isValueTextUnderlined) {
            tv_value.paintFlags = (tv_value.paintFlags | Paint.UNDERLINE_TEXT_FLAG)
        } else
            tv_value.paintFlags =
    }*/

    fun setValueWithUnderline(value: String?, isValueTextUnderlined: Boolean = false) {
        tv_value.text = if (value.isNullOrEmpty() || value.toLower() == "nan")
            context.getString(R.string.na) else if (isValueTextUnderlined) {
            val spanString = SpannableString(value)
            spanString.setSpan(UnderlineSpan(), 0, value.length, 0)
            spanString
        } else value
    }

    fun setValueTextColor(@ColorInt valueTextColor: Int) {
        tv_value.setTextColor(valueTextColor)
    }

    fun setDrawable(drawableResId: Int) {
        if (drawableResId > 0) {
            tv_value.compoundDrawablePadding =
                context.resources.getDimensionPixelOffset(R.dimen.dimenDrawablePadding)
            tv_value.setCompoundDrawablesWithIntrinsicBounds(
                AppCompatResources.getDrawable(context, drawableResId), null, null, null
            )
        }
    }

    fun setDrawableToValue(resId: Int) {
        tv_value.setCompoundDrawablesWithIntrinsicBounds(0, 0, resId, 0)
        val padding = resources.getDimensionPixelOffset(R.dimen.dimenDrawablePadding)
        tv_value.compoundDrawablePadding = padding
    }

    fun setValue(value: String?) {
        tv_value.text = if (value.isNullOrEmpty() || value.toLower() == "nan")
            context.getString(R.string.na)
        else
            value
    }

    fun setValue(value: Int?) {
        tv_value.text = value?.toString() ?: context.getString(R.string.na)
    }

    fun getValue(): String {
        return tv_value.text.toString()
    }

    fun setLabel(value: String?) {
        tv_label.text = value
    }

    fun setData(labelText: String?, valueText: String?) {
        tv_label.text = labelText
        tv_value.text =
            if (valueText.isNullOrEmpty() || valueText.equals("nan", ignoreCase = true))
                context.getString(R.string.na)
            else
                valueText
    }

    fun setData(labelText: String?, valueText: Double?) {
        tv_label.text = labelText
        valueText?.let {
            tv_value.text = if (it > 0.0)
                valueText.getTwoDecimalValueInString(context.getString(R.string.na))
            else
                context.getString(R.string.na)
        } ?: context.getString(R.string.na)
    }

    fun setData(labelText: String?, valueText: Int?) {
        tv_label.text = labelText
        tv_value.text = valueText?.toString() ?: context.getString(R.string.na)
    }

    fun setData(labelText: String?, valueText: Long?) {
        tv_label.text = labelText
        tv_value.text = valueText?.toString() ?: context.getString(R.string.na)
    }

    fun setData(labelText: String?, valueText: Boolean) {
        tv_label.text = labelText
        tv_value.text = if (valueText)
            context.getString(R.string.yes)
        else
            context.getString(R.string.no)

    }

    fun setData(labelText: String?, valueText: Float?) {
        tv_label.text = labelText
        tv_value.text = if (valueText != null && valueText > 0.0f)
            valueText.getTwoDecimalValueInString(context.getString(R.string.na))
        else
            context.getString(R.string.na)
    }

    fun setDollarData(labelText: String?, valueText: String?) {
        tv_label.text = labelText
        tv_value.text =
            if (valueText.isNullOrEmpty() || valueText.toLower() == "nan")
                context.getString(R.string.na)
            else
                context.getString(R.string.dollar_text, valueText)
    }


    fun setDrawableAtLabel(resId: Int) {
        tv_label.setCompoundDrawablesWithIntrinsicBounds(0, 0, resId, 0)
    }


}