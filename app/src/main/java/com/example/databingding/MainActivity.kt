package com.example.databingding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.example.databingding.beans.User
import com.example.databingding.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mHandler = Handler()

        // 绑定
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.user = User("SunnyDay")

        // 5min 后更新下内容。
        thread {
            Thread.sleep(5*1000)
            mHandler.post {
                binding.user = User("Tom")
            }
        }

    }
}