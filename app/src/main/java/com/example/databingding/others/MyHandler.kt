package com.example.databingding.others

import android.util.Log
import android.view.View

class MyHandler {
    private val TAG = MyHandler::class.java.simpleName
    fun onClickFriend(view: View) {
        Log.d(TAG, "onClickFriend")
    }

    fun saveTask(taskName: String):String{
        Log.d(TAG, "saveTask:$taskName")
        return "aaa"
    }
}