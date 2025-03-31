/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.rkmbed.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.intellij.lang.annotations.Language
import java.io.File

abstract class GenerateResourcesTask : DefaultTask() {
    @TaskAction
    fun generate() {
        val resourcesDir = project.layout.projectDirectory.dir("src/commonMain/resources")
        val outputDir = project.layout.buildDirectory.dir("generated/kotlin").get().asFile
        val outputFile = File(outputDir, "_RKMbed_GeneratedResources.kt")
        if (!resourcesDir.asFile.exists()) return
        val files = resourcesDir.asFileTree.files
        val generatedCode = buildString {
            appendLine("// 此文件为自动生成, 请勿手动修改! | This file is auto-generated, please DO NOT edit it by hand!")
            appendLine()
            appendLine("package ${project.extensions.findByType(RKMbedProjectExtension::class.java)?.packageName?.get() ?: "com.example.resources"}")
            appendLine()
            appendLine(
                @Language("kotlin")
                "private val res: Map<String, AbstractResource> = mapOf<String, AbstractResource>("
            )
            files.forEach {
                appendLine(
                    "\"${
                        it.path.split("src${File.separator}commonMain${File.separator}resources${File.separator}")
                            .last()
                    }\" to AbstractResource(${it.toByteArrayOf()})"
                )
            }
            appendLine(
                """)
                    
public fun getResources(path: String): AbstractResource {
    return requireNotNull(res[path]) { "资源不存在!" }
}

public class AbstractResource(private val source: ByteArray) {
    public fun asByteArray(): ByteArray {
        return source
    }

    public fun asString(): String {
        return source.decodeToString()
    }
}"""
            )
        }
        outputFile.parentFile.mkdirs()
        outputFile.writeText(generatedCode)
    }
}