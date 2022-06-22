package com.example.databingding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.databingding.fragment.TestFragment

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,TestFragment.getInstance())
            .commit()
    }
}