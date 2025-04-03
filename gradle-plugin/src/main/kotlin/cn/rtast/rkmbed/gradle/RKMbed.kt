/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.rkmbed.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import java.io.File

@Suppress("unused")
class RKMbed : Plugin<Project> {
    override fun apply(target: Project) {
        target.extensions.create("rkmbed", RKMbedProjectExtension::class.java)
        target.tasks.register("generateResources", GenerateResourcesTask::class.java) {
            it.group = "rkmbed"
        }
        val extensionConfig = target.extensions.getByType(RKMbedProjectExtension::class.java)
        target.afterEvaluate {
            if (extensionConfig.mergeResources.get() == true) {
                File("build/generated/kotlin/").mkdirs()
                target.extensions.getByType(KotlinMultiplatformExtension::class.java)
                    .sourceSets.findByName("commonMain")?.kotlin?.srcDir("build/generated/kotlin/")
            } else {
                extensionConfig.mappedResourcesPath.get().forEach {
                    File("build/generated/kotlin/${it.value.targetName}").mkdirs()
                    target.extensions.getByType(KotlinMultiplatformExtension::class.java)
                        .sourceSets.findByName(it.value.targetName)?.kotlin
                        ?.srcDir("build/generated/kotlin/${it.value.targetName}")
                }
            }
        }
    }
}