package com.example.databingding.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.databingding.R
import com.example.databingding.databinding.FragmentTestBinding

/**
 * A simple [Fragment] subclass.
 */
class TestFragment : Fragment() {

    private lateinit var binding: FragmentTestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test, container, false)
        binding.newText = "changeText"
        binding.fragment = this@TestFragment
        return binding.root
    }

    companion object {
        fun getInstance() = TestFragment()
    }

    fun changeText(){
        binding.newText = "TestFragment"
    }
}