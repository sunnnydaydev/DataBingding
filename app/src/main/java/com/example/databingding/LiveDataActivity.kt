package com.example.databingding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.databingding.databinding.ActivityLiveDataBinding
import com.example.databingding.model.MyViewModel

class LiveDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLiveDataBinding
    private lateinit var viewModel: MyViewModel
    private var num = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_live_data)
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)


        binding.activity = this
        binding.model = viewModel
        //设置lifecycleOwner，接管LiveData 数据变化。不设置不起作用。
        binding.lifecycleOwner = this
    }

    fun add(view: View) {
        viewModel.setNum(++num)
    }
}