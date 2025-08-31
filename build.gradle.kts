plugins {
    kotlin("multiplatform") version "2.1.20" apply false
    kotlin("jvm") version "2.1.20" apply false
    id("maven-publish")
}

val pluginVersion: String by extra

allprojects {
    group = "cn.rtast.kembeddable"
    version = pluginVersion

    repositories {
        mavenCentral()
        mavenLocal()
        maven("https://repo.maven.rtast.cn/releases")
    }
}

subprojects {
    apply(plugin = "maven-publish")

    afterEvaluate {
        publishing {
            repositories {
                maven("https://repo.maven.rtast.cn/releases/") {
                    credentials {
                        username = "RTAkland"
                        password = System.getenv("PUBLISH_TOKEN")
                    }
                }
            }
        }
    }
}