package com.example.databingding

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databingding.beans.User
import com.example.databingding.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mHandler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorView: ViewGroup = window.decorView as ViewGroup
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.activity_main,decorView,true)
        // 绑定
       // binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // 初始值
        binding.user = User("SunnyDay")

        updateTextByTargetSecondLater(5 * 1000)
    }

    /**
     * second秒后更新下内容。
     * */
    private fun updateTextByTargetSecondLater(second: Long) {
        thread {
            Thread.sleep(second)
            mHandler.post {
                binding.user = User("Tom")
            }
        }
    }
}