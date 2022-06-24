package com.example.databingding.extensions

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.databingding.R

/**
 * Create by SunnyDay 2022/06/24 19:43:11
 */
@BindingAdapter("android:text")
fun setTextPlus(view: TextView){
    view.textSize = 18f
    view.setTextColor(view.context.getColor(R.color.purple_500))
}