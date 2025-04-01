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

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation("cn.rtast.rkmbed:runtime:<version>")
            }
        }
    }
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
fun main() {
    println(getResouerce("config.json").asString())
}
```

> 最新版本可以在这里找到 https://repo.maven.rtast.cn/#/releases/cn/rtast/rkmbed/gradle-plugin


# Usage

Just add plugin and add repository

> The latest version can be found at https://repo.maven.rtast.cn/#/releases/cn/rtast/rkmbed/gradle-plugin

