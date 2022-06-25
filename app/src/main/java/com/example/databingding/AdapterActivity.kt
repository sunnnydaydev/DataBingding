package com.example.databingding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.databingding.databinding.ActivityAdapterBinding
import com.example.databingding.generated.callback.OnClickListener
import com.example.databingding.others.MyOnClickListener
import kotlinx.android.synthetic.main.activity_adapter.*


class AdapterActivity : AppCompatActivity() {
    private val binding: ActivityAdapterBinding by lazy {
        DataBindingUtil.setContentView(this@AdapterActivity, R.layout.activity_adapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.listener = MyOnClickListener()
    }
}

