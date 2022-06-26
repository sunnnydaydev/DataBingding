package com.example.databingding.beans

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.example.databingding.BR

class Student(): BaseObservable(){
    @get:Bindable
    var stuName = ""
    set(value) {
        field = value
        notifyPropertyChanged(BR.stuName)
    }
    //ObservableField 可替换基本数据类型字段.
    val age: ObservableField<Int> = ObservableField()
}
//1、若不引入kapt插件，引入BaseObservable后编译报错。无法生成自定义类的BR字段。Unresolved reference: BR

//2、BR包有两个，引入哪个都能跑起来。不知道有啥区别。
// androidx.databinding.library.baseAdapters.BR
// androidx.databinding.library.baseAdapters.BR