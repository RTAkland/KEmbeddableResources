plugins {
    id("cn.rtast.kembeddable") version "1.3.1"
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
            entryPoint = "test.main"
        }
    }
    mingwX64 {
        binaries.executable {
            entryPoint = "test.main"
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.7.0")
            }
        }
    }
}

kembeddable {
    compression = true
    resourcePath.apply {
        put("commonMain/resources", "common")
        put("nativeMain/resources", "native")
    }
    publicGeneratedResourceVariable = true
}