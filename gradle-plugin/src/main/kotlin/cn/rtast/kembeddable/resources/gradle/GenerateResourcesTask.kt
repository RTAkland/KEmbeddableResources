/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.kembeddable.resources.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputFiles
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class GenerateResourcesTask : DefaultTask() {

    private val sep = File.separator

    @TaskAction
    fun generate() {
        val settings = project.extensions.findByType(KEmbeddableResourcesExtension::class.java)!!
        val usingCompression = settings.compression.get()
        val outputDir = project.layout.buildDirectory.dir("generated/kotlin").get().asFile
        val isGenerateResourceVariablePublic = settings.publicGeneratedResourceVariable.get()
        val visibility = if (isGenerateResourceVariablePublic) "public" else "private"
        settings.resourcePath.get().forEach { (resourcePath, packageName) ->
            val outputFile = File(outputDir, "_kembed_generated_${resourcePath}.kt")
            val sourceSetName = resourcePath.split(sep).first().replaceFirstChar { it.uppercaseChar() }
            val generatedVarianceName = "kembed${sourceSetName}Resources"
            outputFile.parentFile.mkdirs()
            val generatedCode = buildString {
                appendCode("// 此文件为自动生成, 请勿手动修改! | This file is auto-generated, please DO NOT edit it by hand!")
                appendLine()
                appendCode("package $packageName")
                appendLine()
                appendCode("""import cn.rtast.kembeddable.resources.runtime.Resource""")
                appendLine()
                appendCode("$visibility val ${generatedVarianceName}: Map<String, Resource> = mapOf<String, Resource>(")
                val resourcesDir = project.layout.projectDirectory.dir("src/$resourcePath")
                val files = if (resourcesDir.asFile.exists()) resourcesDir.asFileTree.files else emptySet()
                files.forEach {
                    appendCode(
                        "\"${
                            it.path.split("src${sep}$resourcePath").last().drop(1)
                        }\" to Resource(${it.toUByteArrayOf(usingCompression)}, $usingCompression),"
                    )
                }
                appendCode(")")
                appendLine()
                appendCode(
                    """
                public fun get${sourceSetName}Resource(path: String): Resource {
                    return requireNotNull($generatedVarianceName[path]) { "资源 ${'$'}path 不存在! | Resource ${'$'}path is not exists!" }
                }""".trimIndent()
                )
            }
            outputFile.writeText(generatedCode)
        }
    }
}