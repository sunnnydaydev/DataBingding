package com.example.databingding.others

import android.util.Log
import android.view.View

class MyHandler {
    private val TAG = MyHandler::class.java.simpleName
    fun onClickFriend(view: View,name:String) {
        Log.d(TAG, "onClickFriend")
    }
}