package com.example.databingding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        middleBlankOp.setOnClickListener {
            startActivity(Intent(this,MiddleBlankActivity::class.java))
        }

        stringAndResource.setOnClickListener {
            startActivity(Intent(this,StringActivity::class.java))
        }
//
//        event.setOnClickListener {
//            startActivity(Intent(this,EventActivity::class.java))
//        }

        observable.setOnClickListener {
            startActivity(Intent(this,ObservableFieldActivity::class.java))
        }

    }

    fun openEvent(view: View) {
        startActivity(Intent(this,EventActivity::class.java))
    }
}