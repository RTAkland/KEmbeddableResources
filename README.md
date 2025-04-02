# 使用方法

```kotlin
// build.gradle.kts
plugins {
    id("cn.rtast.rkmbed") version "<version>"
}

repositories {
    mavenCentral()
    // 添加RTAST的maven仓库
    // Add the maven repository of RTAST
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

rkmbed {
    // 指定生成的资源类的包名
    // Specify auto-generated resources source code package
    packageName = "cn.rtast.test.resources"
    // 添加别的源码集的资源文件夹(默认列表中只有"commonMain/resources")
    // Add others sourceSet's resources folder (This list only contains "commonMain/resources" by default)  
    resourcePath.add("nativeMain/resources")
}

// settings.gradle.kts
pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        // 添加RTAST的maven仓库
        // Add the maven repository of RTAST
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

Just add plugin, add repository, configure plugin, then enjoy it~

> The latest version can be found at https://repo.maven.rtast.cn/#/releases/cn/rtast/rkmbed/gradle-plugin

