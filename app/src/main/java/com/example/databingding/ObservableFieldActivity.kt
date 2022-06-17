package com.example.databingding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databingding.databinding.ActivityObservableFieldBinding
import com.example.databingding.others.MyHandler

class ObservableFieldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityObservableFieldBinding = DataBindingUtil.setContentView(this,R.layout.activity_observable_field)
        binding.handler = MyHandler()

        // 参考：https://www.jianshu.com/p/09d98b0b081b
    }
}