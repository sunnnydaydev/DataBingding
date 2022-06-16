package com.example.databingding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.example.databingding.databinding.ActivityEventBinding
import com.example.databingding.databinding.ActivityMiddleBlankBinding
import com.example.databingding.others.MyHandler

class EventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityEventBinding = DataBindingUtil.setContentView(this,R.layout.activity_event)
        binding.handler = MyHandler()
    }
}