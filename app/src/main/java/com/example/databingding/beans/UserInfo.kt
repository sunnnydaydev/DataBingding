package com.example.databingding.beans

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.databingding.BR

/**
 * Create by SunnyDay 2022/06/26 16:25:20
 */
class UserInfo (): BaseObservable() {

    private var uName: String? = "defaultName"

    @Bindable
    fun getUserName() :String?{
        return uName
    }

    /**
     * 注意BR字段的生成规则：
     * 1、与getter、setter有关，对应@Bindable注解的字段。这里为userName 而不是uName。
     * */
    fun setUserName(newName: String?) {
        if (uName != newName) {
            uName = newName
            notifyPropertyChanged(BR.userName)
            Log.d("UserInfo","UserInfo changed:${uName}")
        }
    }
}