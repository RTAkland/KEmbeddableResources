/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.rkmbed.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("unused")
class RKMbed : Plugin<Project> {
    override fun apply(target: Project) {
        target.extensions.create("rkmbed", RKMbedProjectExtension::class.java)
        target.tasks.register("generateResources", GenerateResourcesTask::class.java) {
            it.group = "rkmbed"
        }
        target.afterEvaluate {
            val kotlinExtension = target.extensions.getByType(KotlinMultiplatformExtension::class.java)
                ?: throw NonMultiplatformProjectException("该插件仅支持多平台项目，请在多平台项目中使用。")
            kotlinExtension.sourceSets.getByName("commonMain") {
                it.dependencies {
                    api("cn.rtast.rkmbed:runtime:${target.version}")
                }
            }
            kotlinExtension.sourceSets.findByName("commonMain")?.kotlin?.srcDir("build/generated/kotlin")
        }
    }
}