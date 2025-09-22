/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.kembeddable.resources.gradle

import cn.rtast.kembeddable.resources.gradle.util.appendCode
import cn.rtast.kembeddable.resources.gradle.util.base64
import cn.rtast.kembeddable.resources.gradle.util.deflate
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class GenerateResourcesTask : DefaultTask() {

    @TaskAction
    fun generateResources() {
        val settings = project.extensions.getByType(KEmbeddableResourcesExtension::class.java)
        val maxKtFileSize = settings.maxSingleFileSize.get() * 1024
        val outputDir = project.layout.buildDirectory.dir("generated/kotlin/kembed").get().asFile
        val compress = settings.compression.get()
        val generatedIndexVisibility = if (settings.publicGeneratedResourceVariable.get()) "public" else "private"
        outputDir.mkdirs()
        val allChunkNames = mutableListOf<String>()
        val inputResourcePaths: List<File> = settings.resourcePath.get()
        val allFiles = inputResourcePaths.flatMap { it.walkTopDown().filter { f -> f.isFile }.toList() }
        var currentKtIndex = 0
        var currentKtSize = 0
        var currentKtContent = StringBuilder("@file:OptIn(ExperimentalUnsignedTypes::class)").apply {
            appendCode("\npackage ${settings.packageName.get()}")
        }
        val resourceIndex = mutableMapOf<String, MutableList<Triple<String, Int, Int>>>()
        fun nextKtFile() {
            if (currentKtContent.isNotEmpty()) {
                val ktFile = File(outputDir, "generated${currentKtIndex}.kt")
                ktFile.writeText(currentKtContent.toString())
                currentKtIndex++
                currentKtSize = 0
                currentKtContent = StringBuilder("@file:OptIn(ExperimentalUnsignedTypes::class)").apply {
                    appendCode("package ${settings.packageName.get()}")
                }
            }
        }

        allFiles.forEach { file ->
            val baseDir = inputResourcePaths.first { file.startsWith(it) }
            val relativePath = baseDir.toPath().relativize(file.toPath()).toString().replace("\\", "/")
            val bytes = file.readBytes()
            var offset = 0
            val chunksForResource = mutableListOf<Triple<String, Int, Int>>()
            while (offset < bytes.size) {
                val remaining = bytes.size - offset
                val spaceLeft = maxKtFileSize - currentKtSize
                val take = minOf(remaining, spaceLeft)
                val chunkBytes = if (compress) bytes.sliceArray(offset until offset + take).deflate()
                else bytes.sliceArray(offset until offset + take)
                val base64Literal = chunkBytes.base64
                val sanitized = relativePath.replace("/", "_").replace(".", "_")
                val chunkName = "${sanitized}_chunk_${currentKtIndex}_$offset"
                currentKtContent.appendLine("internal val `$chunkName` = \"$base64Literal\"")
                currentKtSize += base64Literal.length
                allChunkNames.add(chunkName)
                chunksForResource.add(Triple(chunkName, 0, chunkBytes.size))
                offset += take
                if (currentKtSize >= maxKtFileSize) nextKtFile()
            }
            resourceIndex[relativePath] = chunksForResource
        }
        nextKtFile()
        val indexFile = File(outputDir, "kembed_res_index.kt")
        val indexContent = buildString {
            appendCode("@file:OptIn(ExperimentalUnsignedTypes::class, ExperimentalEncodingApi::class)")
            appendCode("package ${settings.packageName.get()}")
            appendCode("import kotlin.io.encoding.ExperimentalEncodingApi")
            appendCode("import cn.rtast.kembeddable.resources.runtime.Resource")
            appendCode("import cn.rtast.kembeddable.resources.runtime.Quad")
            appendCode("import kotlin.io.encoding.Base64")
            appendCode("$generatedIndexVisibility val resourceIndex: Map<String, List<Quad<String, Int, Int, Boolean>>> = mapOf(")
            resourceIndex.forEach { (path, chunks) ->
                appendLine("\"$path\" to listOf(")
                chunks.forEach { (name, offset, length) ->
                    appendCode("Quad(\"$name\", $offset, $length, ${compress}),")
                }
                appendCode("),")
            }
            appendCode(")")
            appendCode("public fun getResource(path: String): Resource {")
            appendCode("  val chunks = resourceIndex[path] ?: error(\"Resource \$path not found\")")
            appendCode("  val totalSize = chunks.sumOf { it.third }")
            appendCode("  val isCompressed = chunks.first().fourth")
            appendCode("  val merged = UByteArray(totalSize)")
            appendCode("  var dst = 0")
            appendCode("  for ((name, offset, length) in chunks) {")
            appendCode("    val chunk = getChunkByName(name)")
            appendCode("    chunk.copyInto(merged, dst, offset, offset + length)")
            appendCode("    dst += length")
            appendCode("  }")
            appendCode("  return Resource(merged, isCompressed)")
            appendCode("}")
            appendCode("public fun getChunkByName(name: String): UByteArray {")
            appendCode("val base64 = when(name) {")
            allChunkNames.forEach { chunk ->
                appendCode("    \"$chunk\" -> `$chunk`")
            }
            appendCode("    else -> error(\"Unknown chunk: \$name\")")
            appendCode("}")
            appendCode("val bytes = Base64.decode(base64)")
            appendCode("return UByteArray(bytes.size) { i -> bytes[i].toUByte() }")
            appendCode("}")
        }
        indexFile.writeText(indexContent)
    }
}