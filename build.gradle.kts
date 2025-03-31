plugins {
    kotlin("multiplatform") version "2.1.20" apply false
    kotlin("jvm") version "2.1.20" apply false
    id("maven-publish")
}

val pluginVersion: String by extra

allprojects {
    group = "cn.rtast.rkmbed"
    version = pluginVersion

    repositories {
        mavenCentral()
        mavenLocal()
    }
}

subprojects {
    apply(plugin = "maven-publish")

    publishing {
        repositories {
            mavenLocal()
            maven("https://maven.rtast.cn/releases/") {
                credentials {
                    username = "RTAkland"
                    password = System.getenv("PUBLISH_TOKEN")
                }
            }
        }
    }
}