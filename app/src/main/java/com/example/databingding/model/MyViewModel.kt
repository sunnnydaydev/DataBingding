package com.example.databingding.model
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    // 使用MutableLiveData观察后，绑定DataBinding 数据更新，DataBinding 便会观察到。
    var number: MutableLiveData<Int> =  MutableLiveData()

    fun setNum(int: Int) {
      number.value = int
    }
}