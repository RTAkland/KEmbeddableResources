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

    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.7.0")
            }
        }
    }
}

tasks.withType<AbstractPublishToMaven>().configureEach {
    onlyIf { false }
}
