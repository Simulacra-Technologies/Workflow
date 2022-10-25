package com.simulacratech.base.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.simulacratech.base.R


class TextAndIconLayout(context: Context, attrs: AttributeSet) :
    LinearLayoutCompat(context, attrs) {
    private var imgView: AppCompatImageView
    private var textView: AppCompatTextView

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.image_and_textview, this, true)
        imgView = findViewById(R.id.iconResource)
        textView = findViewById(R.id.textContent)
        attrs.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.TextAndIconLayout, 0, 0)
            val labelString = typedArray.getString(R.styleable.TextAndIconLayout_textContent)
            val iconImage = typedArray.getResourceId(R.styleable.TextAndIconLayout_iconResource, -1)
            setLabel(labelString)
            setIconImage(iconImage)
            typedArray.recycle()
        }
    }

    fun setIconImage(iconImage: Int) {
        imgView.setImageResource(iconImage)
    }

    fun setLabel(labelString: String?) {
        textView.text = labelString
    }
}