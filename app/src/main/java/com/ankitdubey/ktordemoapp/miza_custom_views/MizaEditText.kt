package com.ankitdubey.ktordemoapp.miza_custom_views

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View.OnTouchListener
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentManager
import com.ankitdubey.ktordemoapp.R
import com.ankitdubey.ktordemoapp.data.toDateStr
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*


class MizaEditText(
    context: Context,
    attributeSet: AttributeSet
) : AppCompatEditText(context, attributeSet) {

    init {

        val typedArray =
            context.theme.obtainStyledAttributes(attributeSet, R.styleable.MizaEditText, 0, 0)

        val borderColor = typedArray.getString(R.styleable.MizaEditText_borderColor)
        val mizaEnable = typedArray.getBoolean(R.styleable.MizaEditText_mizaETEnable, true)
        if (!mizaEnable) {
            disable()
        }

        if (borderColor == null || borderColor == "1")
            background = ContextCompat.getDrawable(context, R.drawable.bg_edit_text)
        else
            background = ContextCompat.getDrawable(context, R.drawable.bg_edit_text_gray)

        val margin12 = resources.getDimension(R.dimen.margin_12).toInt()
        val margin16 = resources.getDimension(R.dimen.margin_16).toInt()
        setPadding(margin12, margin16, margin12, margin16)

        typeface = ResourcesCompat.getFont(context, R.font.notosanc_regular)
        textSize = 16F

        setMizaInputType(typedArray)

        typedArray.recycle()
    }

    private fun setMizaInputType(typedArray: TypedArray) {
        when (typedArray.getString(R.styleable.MizaEditText_mizaInputType)) {
            "2" -> inputType = InputType.TYPE_CLASS_NUMBER
            "3" -> inputType = InputType.TYPE_CLASS_PHONE
            "4" -> inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            "5" -> setPasswordType()
            "6" -> setCalendarType()
            else -> inputType = InputType.TYPE_CLASS_TEXT
        }
    }

    private fun setCalendarType() {
        isFocusable = false
        setDrawableRight(R.drawable.ic_baseline_date_range_24)
    }

    fun selectDate(fragmentManager: FragmentManager, onDateSelected : (Date) -> Unit){
        val picker = MaterialDatePicker.Builder.datePicker().build()
        picker.show(fragmentManager,"DOB")

        picker.addOnPositiveButtonClickListener {
            val date = Date(it)
            setText(date.toDateStr("dd/MM/yyyy"))
            onDateSelected(Date(it))
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setPasswordType() {
        inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        togglePassword(true)

        setOnTouchListener(OnTouchListener { v, event ->
            val drawableRight = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= right - compoundDrawables[drawableRight].bounds.width()
                ) {
                    // your action here
                    if (transformationMethod == null)
                        togglePassword(true)
                    else
                        togglePassword(false)

                    return@OnTouchListener true
                }
            }
            false
        })
    }

    private fun togglePassword(enable: Boolean) {
        if (enable) {
            setDrawableRight(R.drawable.ic_pwd_visible_off)
            transformationMethod = PasswordTransformationMethod.getInstance()
        } else {
            setDrawableRight(R.drawable.ic_pwd_visible)
            transformationMethod = null
        }
    }

    fun setDrawableRight(drawable : Int){
        setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
    }

    fun disable() {
        setTextColor(ContextCompat.getColor(context, R.color.colorDisable))
        isFocusable = false
        isClickable = false
    }

    fun enable() {
        setTextColor(ContextCompat.getColor(context, R.color.black))
        isFocusable = true
        isClickable = true
    }

}