package com.example.databingding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databingding.databinding.ActivityMiddleBlankBinding
import com.example.databingding.databinding.ActivityStringBinding
/**
 * 字符串、res资源的使用。
 * */
class StringActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityStringBinding = DataBindingUtil.setContentView(this,R.layout.activity_string)
    }
}