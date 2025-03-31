/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.rkmbed.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class RKMbed : Plugin<Project> {
    override fun apply(target: Project) {
        target.extensions.create("rkmbed", RKMbedProjectExtension::class.java)
        target.tasks.register("generateResources", GenerateResourcesTask::class.java) {
            it.group = "rkmbed"
        }
        target.afterEvaluate {
            target.extensions.getByType(KotlinMultiplatformExtension::class.java)
                .sourceSets.findByName("commonMain")?.kotlin?.srcDir("build/generated/kotlin")
        }
    }
}