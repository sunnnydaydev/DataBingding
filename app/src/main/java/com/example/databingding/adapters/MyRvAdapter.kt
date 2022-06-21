package com.example.databingding.adapters

import android.app.Activity
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.databingding.R
import com.example.databingding.databinding.GameBinding

class MyRvAdapter(private val data: List<String>, private val context: Activity) :
    RecyclerView.Adapter<MyRvAdapter.MyViewHolder>() {

    /**
     * 一、未使用DataBinding时:
     * 1、构造需要定义个rootView
     * 2、rootView传递给RecyclerView.ViewHolder的构造一份。
     * 3、自定义的ViewHolder中需要定义一些布局字段，做好findViewById操作。
     * 4、onBindViewHolder中获取ViewHolder操作view。
     *
     * 二、使用DataBinding后:
     * 1、构造中替换为binding对象。
     * 2、给父类构造通过binding对象传递跟View
     * 3、提供个返回binding对象的方法即可，方便onBindViewHolder中使用
     * ps：findViewById直接就可省略了，自定义的ViewHolder中贼整洁。
     * */
    class MyViewHolder(private val binding: GameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun getBinding() = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: GameBinding =
            DataBindingUtil.inflate(context.layoutInflater, R.layout.game, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // 这里直接传递给textView，或者通过变量传递都一样。
        // 1、通过xml 布局定义的变量
        holder.getBinding().gameName= data[position]
        //2、通过直接操作TextView
       // holder.getBinding().name.text= data[position]
    }

    override fun getItemCount(): Int {
        return if (data.isEmpty()) {
            0
        } else {
            data.size
        }
    }
}