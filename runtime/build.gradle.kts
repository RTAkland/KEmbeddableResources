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
    jvm {
        compilerOptions.jvmTarget = JvmTarget.JVM_1_8
    }

    sourceSets {
        commonMain.dependencies {
            implementation("cn.rtast.kzlib:kzlib:0.0.1")
        }

        jvmMain.dependencies {

        }

        nativeMain.dependencies {

        }
    }
}