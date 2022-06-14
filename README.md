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
        
        <variable name="index"
            type="Int"/>
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
        //操作user属性
        binding.user = User("SunnyDay")
        // 操作控件。
        binding.mTextView.text = "Kate"
        
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
- 系统会为每个布局文件生成一个绑定类，默认情况下类名基于布局名，转为大驼峰命名并以Binding结尾。如activity_main.xml对应绑定类ActivityMainBinding
- 绑定类中拥有布局中所有属性。（variable中定义的变量、xml控件对象）
- DataBindingUtil.setContentView和DataBindingUtil.inflate 都可达到一致的功能如下：

```kotlin
        val decorView: ViewGroup = window.decorView as ViewGroup
        //注意倒数第二个参数ViewGroup不能为空，最后一个参数必须为true 否则看不到UI效果。
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.activity_main,decorView,true)
```
###### 3、表达式语言

我们可以在@{}的大括号中书写表达式语言，也即{}中可进行算术运算、方法调用、字段访问等等。如下栗子：

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <!--系统类要使用import方式导包，否则下面xml控件无法使用表达式如View.Gone 无法调用-->
        <import type="android.view.View" />
        
        <!-- 小于号< 需要转义为 &lt; 语法要求 直接写 List<String> 是语法错误的。-->
        <import type="java.util.List"/>
        <variable name="list" type="List&lt;String>"/>

        <variable
            name="user"
            type="com.example.databingding.beans.User" />

        <variable
            name="index"
            type="Integer" />
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

        <TextView
            android:id="@+id/mTextView2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{String.valueOf(index+1)}"
            android:textSize="15sp"
            android:visibility="@{index > 2 ? View.GONE:View.VISIBLE}"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/mTextView" />

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>

```
留意：

- 自定义类的类型使用type定义的，导包即可。系统类要使用import方式导包。
- 表达式有很多无非是在@{}中写点表达式代码，具体支持的表达式参考[官方文档#表达式语言](https://developer.android.google.cn/topic/libraries/data-binding/expressions#expression_language)
- 表达式轴不支持this、super、new、显式泛型调用。

常见的表达式：

（1）Null合并运算符？？可简化为空判断情况下的三目运算。

如果左边运算数非null，则 Null 合并运算符 (??) 选择左边运算数，如果左边运算数为 null，则选择右边运算数。

```xml
<TextView
    android:id="@+id/mTextView2"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@{user.displayName ?? user.lastName}" />
```

（2）数据绑定代码会自动检测避免出现Null指针异常。

例如，在表达式 @{user.name} 中，如果 user 为 Null，则为 user.name 分配默认值 null。如果您引用 user.age，其中 age 的类型为 int，则数据绑定使用默认值 0。

（3）其他控件引用

控件id则会生成对应控件对象，通过控件对象则可访问控件属性。

（4）集合、Map的访问

为方便起见，可使用 [] 运算符访问常见集合，Map。



[官方文档](https://developer.android.google.cn/topic/libraries/data-binding)