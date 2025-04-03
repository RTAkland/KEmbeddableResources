plugins {
    id("cn.rtast.rkmbed") version "1.1.4"
    kotlin("multiplatform")
}

repositories {
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
}

tasks.withType<AbstractPublishToMaven>().configureEach {
    onlyIf { false }
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
                implementation(project(":runtime"))
            }
        }
    }
}

rkmbed {
    packageName = "cn.rtast.test.resources"
    resourcePath.add("nativeMain/resources")
}