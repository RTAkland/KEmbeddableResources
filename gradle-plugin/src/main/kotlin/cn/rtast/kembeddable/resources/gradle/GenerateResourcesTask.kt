/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.kembeddable.resources.gradle


import cn.rtast.kembeddable.resources.gradle.util.clearDirectory
import cn.rtast.kembeddable.resources.gradle.util.generateIndex
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class GenerateResourcesTask : DefaultTask() {

    @TaskAction
    fun generateResources() {
        val settings = project.extensions.getByType(KEmbeddableResourcesExtension::class.java)
        val maxKtFileSize = settings.maxSingleFileSize.get() * 1024
        val outputDir = project.layout.buildDirectory.dir("generated/kotlin/kembed").get().asFile.apply {
            clearDirectory(this)
            mkdirs()
        }
        val compression = settings.compression.get()
        val generatedIndexVisibility = if (settings.publicGeneratedResourceVariable.get()) "public" else "internal"
        val packageName = settings.packageName.get()
        val inputDirs = settings.resourcePath.get()
        inputDirs.generateIndex(outputDir, packageName, generatedIndexVisibility, maxKtFileSize, compression)
    }
}