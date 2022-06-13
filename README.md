# DataBinding 数据绑定库

# 一、环境配置

###### 1、环境要求

- Android4.0(Api 14)及其以上
- Android Plugin for Gradle 1.5.0 及其以上

###### 2、开启DataBinding库的支持

app/build.gradle 中添加如下代码：

```gradle
android {
        dataBinding {
            enabled = true
        }
    }
```
# 二、布局和绑定达式

###### 1、布局文件的小差异
数据绑定的布局文件相对我们写的普通xml文件还是有点小差异的：
- 要以layout标签为跟标签，
- 使用variable标签在xml中定义变量与变量类型
- 使用@{}来访问定义的变量

来个栗子：
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>

        <variable
            name="user"
            type="com.example.databingding.beans.User" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

        <TextView
            android:id="@+id/mTextView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{user.userName}"
            android:textSize="15sp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>

```

注意下细节：
- variable 标签要放置到 data标签下
- variable标签的name属性定义变量名，type属性声明变量类型。
- User为我们自定义的一个类，自定义类的属性一定不能为private访问权限，否则xml中无法使用表达式访问到。

###### 2、进行数据绑定

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mHandler = Handler()
        //数据绑定
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.user = User("SunnyDay")
        // 5min 后更新下内容。
        thread {
            Thread.sleep(5*1000)
            mHandler.post {
                binding.user = User("Tom")
            }
        }

    }
}
```

[官方文档](https://developer.android.google.cn/topic/libraries/data-binding)