package com.example.databingding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databingding.beans.UserInfo
import com.example.databingding.databinding.ActivityEachOtherBinding

class EachOtherActivity : AppCompatActivity() {
  private  val binding : ActivityEachOtherBinding   by lazy {
        DataBindingUtil.setContentView(this,R.layout.activity_each_other)
    }

    private val userInfo = UserInfo().apply {
        setUserName("SunnyDay")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.userInfo = userInfo
    }
}