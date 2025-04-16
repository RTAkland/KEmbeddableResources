import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
}

kotlin {
    explicitApi()
    linuxArm64()
    linuxX64()
    mingwX64()
    macosX64()
    macosArm64()
    jvm { compilerOptions.jvmTarget = JvmTarget.JVM_1_8 }
    tvosSimulatorArm64()
    tvosX64()
    tvosX64()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    watchosDeviceArm64()
    watchosSimulatorArm64()
    watchosArm64()
    watchosX64()
    watchosArm32()

    sourceSets {
        commonMain.dependencies {
            implementation("cn.rtast.kzlib:kzlib:0.0.4")
        }
    }
}