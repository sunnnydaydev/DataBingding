package com.example.databingding.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods

/**
 * Create by SunnyDay 2022/06/23 21:41:51
 */


@BindingMethods(
    BindingMethod(type = TextView::class,
        attribute = "log",
        method ="setMyLog" )
,    BindingMethod(type = Button::class,
        attribute = "android:text",
        method ="setTextSize" )
)
class MyTextView@JvmOverloads constructor(context: Context,attributeSet: AttributeSet?,defStyle:Int = 0): TextView(context,attributeSet,defStyle) {
    fun setMyLog(msg:String){
        Log.d("tag",msg)
    }
}