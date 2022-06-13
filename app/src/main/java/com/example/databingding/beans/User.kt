package com.example.databingding.beans

data class User(
     // 发现：这里成员不能私有（private）否则布局中引用不到。
     val userName: String
)