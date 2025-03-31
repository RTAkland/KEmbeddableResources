plugins {
    kotlin("jvm")
    id("com.gradle.plugin-publish") version "1.2.1"
    `java-gradle-plugin`
}

gradlePlugin {
    website = "https://github.com/RTAkland/RKMbed"
    vcsUrl = "https://github.com/RTAkland/RKMbed"
    plugins {
        create("rkmbed") {
            id = "cn.rtast.rkmbed"
            displayName = "RKMBed"
            description = "Auto generate resources on kmp"
            tags = listOf("resources", "kmp", "auto-generated")
            implementationClass = "cn.rtast.rkmbed.gradle.RKMbed"
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.20")
}