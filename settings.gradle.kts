rootProject.name = "RKMbed"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
        maven("https://repo.maven.rtast.cn/releases")
    }
}

include(":gradle-plugin")
include(":runtime")
include(":runtime-filesystem")
include(":test-plugin")
