package com.example.databingding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.databingding.beans.Student
import com.example.databingding.databinding.ActivityObservableFieldBinding

class ObservableFieldActivity : AppCompatActivity() {
    //绑定
    private val binding: ActivityObservableFieldBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_observable_field)
    }
    private val stu1 = Student().apply {
        stuName = "SunnyDay"
    }
    private val stu2 = Student().apply {
        stuName = "Tom"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVariableInXml()
    }

    /**
     * 对xml中的变量进行初始化值
     * */
    private fun initVariableInXml(){
        binding.student = stu1
        binding.activity = this
    }

    fun changeText(view: View) {
        stu1.stuName = "Kate"  // 不起作用
       // binding.student = stu2
    }
}