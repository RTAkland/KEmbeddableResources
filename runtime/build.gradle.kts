import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
}

kotlin {
    explicitApi()
    linuxX64()
    linuxArm64()
    mingwX64()
    macosX64()
    macosArm64()
    jvm {
        compilerOptions.jvmTarget = JvmTarget.JVM_1_8
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.7.0")
            }
        }
    }
}