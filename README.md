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

# 支持的特性

- 轻量级, 没有外部依赖
- 支持混合resources文件夹（可以配置多个sourceSet的resources文件夹, 随后会将资源自动合并）
- 支持为不同的sourceSet创建不同的代码生成配置

# 不支持的特性

- Zlib压缩(欢迎PR, 请尽量使用内置的api, 例如Posix.zlib)

# 鸣谢

<div>

<img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jetbrains.png" alt="JetBrainsIcon" width="128">

<a href="https://www.jetbrains.com/opensource/"><code>JetBrains Open Source</code></a> 提供的强大IDE支持

</div>

此项目受到 [kMbed](https://git.karmakrafts.dev/kk/kmbed) 启发

但是本项目是一个轻量级的插件, 没有任何外部依赖, 只需要添加插件到gradle然后简单配置就可以使用了

# Usage

Just add plugin, add repository, configure plugin, then enjoy it~

> The latest version can be found at https://repo.maven.rtast.cn/#/releases/cn/rtast/rkmbed/gradle-plugin

# Supported Features

- lightweight and no dependencies
- Support mixed resources(Auto merge configured resources into a single file)
- Support split resources for single sourceSet

# Unsupported Features

- Zlib compression(PR are welcome, please use built-in API, such as Posix.zlib)

# Thanks

<div>

<img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jetbrains.png" alt="JetBrainsIcon" width="128">

<a href="https://www.jetbrains.com/opensource/"><code>JetBrains Open Source</code></a> provided the powerful IDE

</div>

This project/plugin is inspired by [kMbed](https://git.karmakrafts.dev/kk/kmbed)