plugins {
//    id("cn.rtast.kembeddable") version "1.2.2"
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
            }
        }
    }
}

//kembeddable {
//    packageName = "cn.rtast.test.resources"
//    resourcePath.add("nativeMain/resources")
//    compression = true
//}