package com.example.databingding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databingding.adapters.MyRvAdapter
import com.example.databingding.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRecyclerViewBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_recycler_view)

        val list = listOf("干将", "猴子", "安琪拉")

        binding.rv.apply {
            adapter = MyRvAdapter(list, this@RecyclerViewActivity)
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
        }
    }
}