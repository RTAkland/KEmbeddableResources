# KEmbeddableResources

> This plugin is used for Kotlin native app development(like ktor native server),
> that can embed the resources into executable binary file,
> and provide some simple & lightweight API to get the resources like this:

```kotlin
fun main() {
    println(getResouerce("config.json").asString())
    println(getResouerce("config.json").asByteArray())
}
```

But this gradle plugin is only compat for KMP project

# Apply plugin

```kotlin
// build.gradle.kts
plugins {
    id("cn.rtast.kembeddable") version "<version>"
}

repositories {
    mavenCentral()
    // Add the maven repository of RTAST
    maven("https://repo.maven.rtast.cn/releases")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                // These two dependencies are only needed when you need to save the file to the filesystem
                implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.7.0")
                implementation("cn.rtast.kembeddable:runtime-filesystem:<version>")
            }
        }
    }
}

kembeddable {
    // Specify auto-generated resources source code package
    packageName = "cn.rtast.test.resources"
    // Add others sourceSet's resources folder (This list only contains "commonMain/resources" by default)  
    resourcePath.add("nativeMain/resources")
    // Enable zlib compression, by default it is `false`
    compression = true
}

// settings.gradle.kts
pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        // Add the maven repository of RTAST
        maven("https://repo.maven.rtast.cn/releases")
    }
}
```

> The runtime module support all of kotlin native target tier(expect android native, js & wasm, because the following
> targets don't need this plugin to embed the resources into binary)

# Generating the resources

```shell
$ ./gradlew generateResources
```

# FileSystem Module

This module provided the API for saving files into filesystem

```kotlin
fun main() {
    val resource: Resource = getResource("xxx.txt")
    resource.saveTo(Path("kotlinx-io/xxx.txt"))  // Kotlinx-io Path
}
```

> The latest version can be found at https://repo.maven.rtast.cn/#/releases/cn/rtast/kembeddable/gradle-plugin
