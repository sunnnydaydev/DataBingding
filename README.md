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
# 二、布局和表达式的绑定


[官方文档](https://developer.android.google.cn/topic/libraries/data-binding)