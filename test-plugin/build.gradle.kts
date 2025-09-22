import cn.rtast.kembeddable.resources.gradle.util.LinuxMain

plugins {
    id("cn.rtast.kembeddable") version "1.3.3"
    kotlin("multiplatform")
}

repositories {
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
}

tasks.withType<AbstractPublishToMaven>().configureEach {
    enabled = false
}

kotlin {
    linuxArm64 {
        binaries.executable {
            entryPoint = "main"
        }
    }
    mingwX64 {
        binaries.executable {
            entryPoint = "main"
        }
    }
    linuxX64 {
        binaries.executable {
            entryPoint = "main"
        }
    }
    js(IR) { browser() }

    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.7.0")
            }
        }
    }
}

kembeddable {
    resourcePath.apply {
        add(LinuxMain)
    }
    publicGeneratedResourceVariable = true
    packageName = "common"
    compression = false
}