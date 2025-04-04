# 使用方法

> 这个插件仅用于Kotlin Multiplatform项目(Native)

```kotlin
// build.gradle.kts
plugins {
    id("cn.rtast.kembeddable") version "<version>"
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
                // 这里两个依赖仅在需要将文件保存到文件系统中时需要
                // These two dependencies are only needed when you need to save the file to the file system
                implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.7.0")
                implementation("cn.rtast.rkmbed:runtime-filesystem:<version>")
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
    println(getResouerce("config.json").asByteArray())
}
```

# 生成资源文件

```shell
$ ./gradlew generateResources
```

# FileSystem模块

这个模块提供了一个拓展函数, 可以保存到文件系统中, 但是依赖于Kotlinx-io

```kotlin
fun main() {
    val resource: Resource = getResource("xxx.txt")
    resource.saveTo(Path("kotlinx-io/xxx.txt"))  // Kotlinx-io Path
}
```

# 不支持的特性

- 不支持使用zlib压缩资源

> 最新版本可以在这里找到 https://repo.maven.rtast.cn/#/releases/cn/rtast/kembeddable/gradle-plugin

# Usage

> This plugin only usage for Kotlin Multiplatform(Native)

Just apply the plugin, add the repository, configure the plugin, then enjoy it~

> The latest version can be found at https://repo.maven.rtast.cn/#/releases/cn/rtast/kembeddable/gradle-plugin

# Generating the resources

```shell
$ ./gradlew generateResources
```

# FileSystem module

This module provides an extension functions to save the resource file into file system, but it depends on kotlinx-io

```kotlin
fun main() {
    val resource: Resource = getResource("xxx.txt")
    resource.saveTo(Path("kotlinx-io/xxx.txt"))  // Kotlinx-io Path
}
```

# Missing features

- Zlib compression are unsupported