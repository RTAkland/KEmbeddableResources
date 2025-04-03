import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
}

kotlin {
    explicitApi()
//    linuxX64 {
//        compilations["main"].cinterops {
//            create("zlibc") {
//                defFile(project.file("src/nativeInterop/cinterop/zlibc-linux64.def"))
//            }
//        }
//    }
//    linuxArm64 {
//        compilations["main"].cinterops {
//            create("zlibc") {
//                defFile(project.file("src/nativeInterop/cinterop/zlibc-linuxarm64.def"))
//            }
//        }
//    }
//    mingwX64 {
//        compilations["main"].cinterops {
//            create("zlibc") {
//                defFile(project.file("src/nativeInterop/cinterop/zlibc-mingw64.def"))
//            }
//        }
//    }
//    macosX64 {
//        compilations["main"].cinterops {
//            create("zlibc") {
//                defFile(project.file("src/nativeInterop/cinterop/zlibc-macos64.def"))
//            }
//        }
//    }
//    macosArm64 {
//        compilations["main"].cinterops {
//            create("zlibc") {
//                defFile(project.file("src/nativeInterop/cinterop/zlibc-macosarm64.def"))
//            }
//        }
//    }
    linuxArm64()
    linuxX64()
    mingwX64()
    macosX64()
    macosArm64()
    jvm {
        compilerOptions.jvmTarget = JvmTarget.JVM_1_8
    }
}