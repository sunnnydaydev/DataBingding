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

        observable.setOnClickListener {
            startActivity(Intent(this,ObservableFieldActivity::class.java))
        }

        eachOther.setOnClickListener {
            startActivity(Intent(this,EachOtherActivity::class.java))
        }
        liveData.setOnClickListener {
            startActivity(Intent(this,LiveDataActivity::class.java))
        }

        recyclerViewActivity.setOnClickListener {
            startActivity(Intent(this,RecyclerViewActivity::class.java))
        }

        fragmentActivity.setOnClickListener {
            startActivity(Intent(this,FragmentActivity::class.java))
        }

    }

    fun openEvent(view: View) {
        startActivity(Intent(this,EventActivity::class.java))
    }
}