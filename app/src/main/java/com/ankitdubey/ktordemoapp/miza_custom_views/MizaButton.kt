package com.ankitdubey.ktordemoapp.miza_custom_views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.ankitdubey.ktordemoapp.R

class MizaButton(
    context: Context,
    attributeSet: AttributeSet
) : AppCompatButton(context, attributeSet) {

    init {
        isAllCaps = false

        val typedArray =
            context.theme.obtainStyledAttributes(attributeSet, R.styleable.MizaButton, 0, 0)
        val buttonType = typedArray.getString(R.styleable.MizaButton_buttonType)
        val mizaEnable = typedArray.getBoolean(R.styleable.MizaButton_mizaEnable, true)

        if (buttonType == null || buttonType == "1") {
            background = ContextCompat.getDrawable(context, R.drawable.bg_btn_primary)
            setTextColor(ContextCompat.getColor(context, R.color.white))
        } else if (buttonType == "2") {
            background = ContextCompat.getDrawable(context, R.drawable.bg_btn_secondary)
            setTextColor(ContextCompat.getColor(context, R.color.purple))
        } else {
            background = ContextCompat.getDrawable(context, R.drawable.bg_btn_transparent)
            setTextColor(ContextCompat.getColor(context, R.color.white))
        }

        typeface = ResourcesCompat.getFont(context, R.font.notosanc_regular)
        textSize = 16F

        if (!mizaEnable)
            disable()

        typedArray.recycle()
    }

    fun disable() {
        isClickable = false
        alpha = 0.5F
    }

    fun enable() {
        isClickable = true
        alpha = 1F
    }

}