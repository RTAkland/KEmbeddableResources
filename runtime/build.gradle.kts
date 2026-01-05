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
    iosX64()
    iosArm64()
    jvm { compilerOptions.jvmTarget = JvmTarget.JVM_1_8 }
    js(IR) { browser() }

    sourceSets {
        commonMain.dependencies {
            implementation("cn.rtast.kzlib:kzlib:0.0.5")
        }
    }
}