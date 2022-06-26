package com.example.databingding.beans

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.databingding.BR

/**
 * Create by SunnyDay 2022/06/26 17:23:20
 *
 * 测试BR类字段生成条件:
 *
 * 1、直接创建字段声明@get:Bindable然后使用notifyPropertyChanged(BR.)时点不出来myTest字段。
 *
 * 2、
 */
class TestBR :BaseObservable(){
    @get:Bindable
    var myTest  = "MyTest"
    set(value) {
        field = value
        notifyPropertyChanged(BR.myTest)
    }
}