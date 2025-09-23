/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 9/24/25
 */


@file:OptIn(ExperimentalEncodingApi::class)

package cn.rtast.kembeddable.resources.gradle.util

import java.io.File
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

private fun File.slice(chunkSize: Int = 64 * 1024, compression: Boolean): List<ByteArray> {
    val data = if (compression) this.readBytes().deflate() else this.readBytes()
    if (data.size <= chunkSize) return listOf(data)
    val result = mutableListOf<ByteArray>()
    var offset = 0
    while (offset < data.size) {
        val end = (offset + chunkSize).coerceAtMost(data.size)
        result.add(data.copyOfRange(offset, end))
        offset += chunkSize
    }
    return result
}

fun List<File>.generateIndex(
    output: File,
    packageName: String,
    visibility: String = "internal",
    chunkSize: Int = 64 * 1024,
    compression: Boolean = false,
) {
    val indexFile = File(output, "resource_index.kt")
    val indexContent = StringBuilder().apply {
        appendLine("package $packageName")
        appendLine("import cn.rtast.kembeddable.resources.runtime.Chunk")
        appendLine("import cn.rtast.kembeddable.resources.runtime.Resource")
        appendLine("private const val isCompression = $compression")
        appendLine("$visibility val resourceIndex: Map<String, Chunk> = mapOf(")
    }
    this.forEach { inputDir ->
        inputDir.walk().filter { it.isFile }.forEach { file ->
            val baseName = file.name.sanitize()
            val slices = file.slice(chunkSize, compression)
            val sliceVarNames = slices.toKtFiles(output, packageName, baseName, visibility)
            indexContent.appendLine("    \"${file.relativeTo(inputDir).path}\" to Chunk(")
            indexContent.appendLine("        name = \"${file.name}\",")
            indexContent.appendLine("        chunks = listOf(")
            sliceVarNames.forEach { sliceName ->
                indexContent.appendLine("            $sliceName,")
            }
            indexContent.appendLine("        )")
            indexContent.appendLine("    ),")
        }
    }
    indexContent.appendLine(")")
    indexContent.apply {
        appendLine("public fun getResource(path: String): Resource {")
        appendLine("val chunk = requireNotNull(resourceIndex[path]) { throw IllegalStateException(\"资源不存在 \$path\") }")
        appendLine("return Resource(chunk.chunks, isCompression)")
        appendLine("}")
    }
    indexFile.writeText(indexContent.toString())
}

fun List<ByteArray>.toKtFiles(
    outputDir: File,
    packageName: String,
    baseName: String,
    visibility: String = "internal",
): List<String> {
    val variableNames = mutableListOf<String>()
    this.forEachIndexed { index, bytes ->
        val variableName = "${baseName}_$index"
        variableNames.add(variableName)
        val subDir = File(outputDir, variableName.take(2))
        subDir.mkdirs()
        val ktFile = File(subDir, "$variableName.kt")
        val base64 = Base64.encode(bytes)
        ktFile.writeText(
            """
            package $packageName
            internal val $variableName = "$base64"
            """.trimIndent()
        )
    }
    return variableNames
}