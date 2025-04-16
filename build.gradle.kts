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
        if (project.name == "test-plugin") {
            tasks.matching { it.name == "publish" }.configureEach {
                onlyIf { false }
            }
        } else {
            publishing {
                repositories {
//                    mavenLocal()
                    maven("http://192.168.10.222:9098/releases/") {
                        isAllowInsecureProtocol = true
                        credentials {
                            username = "RTAkland"
                            password = System.getenv("PUBLISH_TOKEN")
                        }
                    }
                }
            }
        }
    }
}