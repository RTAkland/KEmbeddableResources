plugins {
    id("cn.rtast.rkmbed") version "0.0.9"
    kotlin("multiplatform")
}

repositories {
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

        }
    }
}

rkmbed {
    packageName = "cn.rtast.test.resources"
}