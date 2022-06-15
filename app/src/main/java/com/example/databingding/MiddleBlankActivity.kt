package com.example.databingding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databingding.databinding.ActivityMiddleBlankBinding

/**
 *[] 运算符 使用
 * */
class MiddleBlankActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityMiddleBlankBinding = DataBindingUtil.setContentView(this,R.layout.activity_middle_blank)

        binding.index = 1
        binding.key = "name"

        binding.list = arrayListOf("集合元素0","集合元素1")
        binding.map = hashMapOf("name" to "SunnyDay","age" to "20")

    }
}