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

        binding.user = User("SunnyDay")//通过绑定类操作布局中定义的user属性
        binding.mTextView.text = "Kate" // 绑定类还可以操作布局中的控件。
        
        // 5sec 后更新下内容。
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
- DataBindingUtil.setContentView和DataBindingUtil.inflate 都可达到一致的功能，如下：

```kotlin
        val decorView: ViewGroup = window.decorView as ViewGroup
        //注意倒数第二个参数ViewGroup不能为空，最后一个参数必须为true 否则看不到UI效果。
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.activity_main,decorView,true)
```
###### 3、表达式语言

我们可以在@{}的大括号中书写表达式语言，如在{}中进行算术运算、方法调用、字段访问等等。举个栗子：

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
        <!--
        主要看这里：
        1、text=""中，我们在@{}快中书写了表达式，而且还进行了算术"+" 运算。
        2、visibility=""中，们在@{}快中书写了表达式，而且还进行了三目运算。     
        -->
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
- DataBinding提供了表达式语言，这个和平常写代码类似无非是在@{}中写点表达式代码。
- 表达式语言约束了支持的运算符和关键字具体可参考[官方文档#表达式语言#常见功能](https://developer.android.google.cn/topic/libraries/data-binding/expressions#expression_language)

（1）不支持的

DataBinding提供了表达式语言不支持：this、super、new、显式泛型调用。

（2）Null合并运算符？？
DataBinding表达式语言提供了空合并运算符，可简化特殊情况下的三目运算。运算符规则如下：

如果左边运算数非null，则 Null 合并运算符 (??) 选择左边运算数，如果左边运算数为 null，则选择右边运算数。

```xml
<TextView
    android:id="@+id/mTextView2"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@{user.displayName ?? user.lastName}" />
```

（3）避免出现空指针异常

数据绑定会自动检测表达式中是否有空值以避免出现Null指针异常。

例如，在表达式 @{user.name} 中，如果 user 为 Null，则为 user.name 分配默认值 null。如果您引用 user.age，其中 age 的类型为 int，则数据绑定使用默认值 0。

（4）其他控件引用

DatBinding会根据控件id以驼峰命名规则自动生成对应控件对象，通过控件对象则可访问控件属性。

（4）List 、Map、Array的访问

为方便起见，可使用 [] 运算符访问常见集合，Map、数组。

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <!-- 小于号< 需要转义为 &lt; 语法要求 直接写 List<String> 是语法错误的。-->
        <import type="java.util.List" />
        <import type="java.util.Map" />

        <variable
            name="index"
            type="Integer" />
        <variable
            name="key"
            type="String" />

        <variable
            name="list"
            type="List&lt;String>" />

        <variable
            name="map"
            type="Map&lt;String, String>" />

    </data>

    <androidx.constraintlayout.widget.ConstraintLayout xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
<!--  
android:text="@{map[key]}"
android:text="@{map.name}"
二者访问结果等效。
-->
        <TextView
            android:id="@+id/mTextView1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{map.name}"
            android:textSize="15sp"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"/>

        <TextView
            android:id="@+id/mTextView2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{list[index]}"
            android:textSize="15sp"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/mTextView1"/>

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>

```
```kotlin
/**
 *[] 运算符 使用
 * */
class MiddleBlankActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityMiddleBlankBinding = DataBindingUtil.setContentView(this,R.layout.activity_middle_blank)

        binding.index = 1
        binding.key = "name"
       
        binding.list = arrayListOf("集合元素0","集合元素1")
        binding.map = hashMapOf("name" to "SunnyDay","age" to "20")
    }
}
```
运行后两控件分别展示：集合元素0、SunnyDay。 这里注意下map的访问方式有两种。

（5）表达式中字符串""的使用

在@{}中直接使用""是编译报错的，可以看到上面map的使用我们还专门定义了个字符串变量比较麻烦，这里看下如何使用字符串。




[官方文档](https://developer.android.google.cn/topic/libraries/data-binding)