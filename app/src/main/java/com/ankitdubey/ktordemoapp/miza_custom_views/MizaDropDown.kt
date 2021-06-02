package com.ankitdubey.ktordemoapp.miza_custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.content.ContextCompat
import com.ankitdubey.ktordemoapp.R

class MizaDropDown(
    context: Context,
    attributeSet: AttributeSet
) : AppCompatSpinner(context, attributeSet) {

    init {

        val typedArray =
            context.theme.obtainStyledAttributes(attributeSet, R.styleable.MizaDropDown, 0, 0)

        val borderColor = typedArray.getString(R.styleable.MizaDropDown_borderType)

        background = if (borderColor == null || borderColor == "1")
            ContextCompat.getDrawable(context, R.drawable.bg_edit_text)
        else
            ContextCompat.getDrawable(context, R.drawable.bg_edit_text_gray)

        val margin5 = resources.getDimension(R.dimen.margin_5).toInt()
        val margin12 = resources.getDimension(R.dimen.margin_12).toInt()
        val margin16 = resources.getDimension(R.dimen.margin_16).toInt()
        setPadding(margin12, margin16, margin5, margin16)

        typedArray.recycle()
    }

    fun setItems(items: List<String>, onItemSelected : (Int) -> Unit) {
        val mAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(context, R.layout.my_spinner_item, items)
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter = mAdapter

        onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                onItemSelected(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

}