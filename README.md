# 使用方法

```kotlin
// build.gradle.kts
plugins {
    id("cn.rtast.rkmbed") version "<version>"
}

repositories {
    mavenCentral()
    maven("https://repo.maven.rtast.cn/releases")
}

// settings.gradle.kts
pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://repo.maven.rtast.cn/releases")
    }
}
```

```kotlin
// 这是自动生成的属性 | This is the auto-generated property
import com.example.resources.GeneratedResource

fun main() {
    // 需要手动初始化 | Need init the property manually
    RKMBedResource.setResource(GeneratedResource)
}
```

> 最新版本可以在这里找到 https://repo.maven.rtast.cn/#/releases/cn/rtast/rkmbed/gradle-plugin


# Usage

Just add plugin and add repository

> The newest version can be found at https://repo.maven.rtast.cn/#/releases/cn/rtast/rkmbed/gradle-plugin

