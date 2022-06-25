package com.example.databingding.extensions

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.databingding.R

/**
 * Create by SunnyDay 2022/06/24 19:43:11
 */
class Extensions {}

/**
 * 自定义AppCompatEditText控件的hint方法逻辑。
 *
 * 当用户设置hint时直接走这里的setTextPlus方法，而不是走AppCompatEditText#setHit方法。
 *
 * */
@BindingAdapter("android:hint")
fun setTextPlus(view: AppCompatEditText, text: String) {
    view.hint = "input your name here"
    view.textSize = 18f
    view.setHintTextColor(view.context.getColor(R.color.purple_500))
}

/**
 * 为price属性设置自定义逻辑
 * */
@BindingAdapter("app:price")
fun setPrice(view: AppCompatEditText, text: String) {
    view.hint = "input your price here!$text"
}

@BindingAdapter(value = ["app:imgUrl", "app:errorImage"], requireAll = false)
fun loadImg(imageView: AppCompatImageView, url: String?, errorImage: Drawable) {
    if (null==url||"" == url) {
        imageView.setImageResource(R.color.purple_500)
    } else{
        Glide
            .with(imageView.context)
            .load(url)
            .placeholder(R.color.purple_500)
            .error(errorImage)
            .into(imageView)
    }
}