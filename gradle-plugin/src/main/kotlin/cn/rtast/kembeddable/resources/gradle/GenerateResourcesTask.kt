/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.kembeddable.resources.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class GenerateResourcesTask : DefaultTask() {

    private val sep = File.separator

    @TaskAction
    fun generate() {
        val settings = project.extensions.findByType(KEmbeddableResourcesExtension::class.java)!!
        val usingCompression = settings.compression.get()
        val outputDir = project.layout.buildDirectory.dir("generated/kotlin").get().asFile
        settings.resourcePath.get().forEach { (resourcePath, packageName) ->
            val outputFile = File(outputDir, "_kembed_generated_${resourcePath}.kt")
            outputFile.parentFile.mkdirs()
            val generatedCode = buildString {
                appendCode("// 此文件为自动生成, 请勿手动修改! | This file is auto-generated, please DO NOT edit it by hand!")
                appendLine()
                appendCode("package $packageName")
                appendLine()
                appendCode("""import cn.rtast.kembeddable.resources.runtime.Resource""")
                appendLine()
                appendCode("private val kembeddableGeneratedResource: Map<String, Resource> = mapOf<String, Resource>(")
                val resourcesDir = project.layout.projectDirectory.dir("src/$resourcePath")
                if (resourcesDir.asFile.exists()) {
                    val files = resourcesDir.asFileTree.files
                    files.forEach {
                        appendCode(
                            "\"${
                                it.path.split("src${sep}$resourcePath").last().drop(1)
                            }\" to Resource(${it.toUByteArrayOf(usingCompression)}, $usingCompression),"
                        )
                    }
                }
                appendCode(")")
                appendLine()
                appendCode(
                    """
                public fun getResource(path: String): Resource {
                    return requireNotNull(kembeddableGeneratedResource[path]) { "资源 ${'$'}path 不存在! | Resource ${'$'}path is not exists!" }
                }""".trimIndent()
                )
            }
            outputFile.writeText(generatedCode)
        }
    }
}