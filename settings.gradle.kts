rootProject.name = "RKMbed"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
}

include(":gradle-plugin")
include(":runtime")
include(":test-plugin")
