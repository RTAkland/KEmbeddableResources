@file:OptIn(ExperimentalEncodingApi::class)

import common.*
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 9/24/25
 */

private fun File.slice(chunkSize: Int = 64 * 1024): List<ByteArray> {
    val data = this.readBytes()
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

fun List<ByteArray>.mergeToFile(): ByteArray {
    ByteArrayOutputStream().use { out ->
        for (chunk in this) {
            out.write(chunk)
        }
        return out.toByteArray()
    }
}

private fun List<ByteArray>.merge(): ByteArray {
    val totalSize = this.sumOf { it.size }
    val result = ByteArray(totalSize)
    var offset = 0
    for (chunk in this) {
        chunk.copyInto(result, offset)
        offset += chunk.size
    }
    return result
}

val generatedList = listOf(
    krepo_backend_js_0,
    krepo_backend_js_1,
    krepo_backend_js_2,
    krepo_backend_js_3,
    krepo_backend_js_4,
    krepo_backend_js_5,
)

public class Resource(bSource: List<String>, private val compressed: Boolean = false) {
    private val source = bSource.map { Base64.decode(it) }.merge()
    public fun asByteArray(): ByteArray = source
    public fun asString(): String = asByteArray().decodeToString()

    private fun List<ByteArray>.merge(): ByteArray {
        val totalSize = this.sumOf { it.size }
        val result = ByteArray(totalSize)
        var offset = 0
        for (chunk in this) {
            chunk.copyInto(result, offset)
            offset += chunk.size
        }
        return result.apply { println(offset) }
    }
}

fun main() {
    val source =
        File("/home/rtakland/IdeaProjects/KEmbeddableResources/test-plugin/src/linuxMain/resources/krepo-backend.js")
    val sliced = source.slice().map { Base64.encode(it) }
    println(sliced == generatedList)
    val res = Resource(sliced).asByteArray()
    println(res.hashCode() == source.readBytes().hashCode())
    File("krepo-backend.js").writeBytes(res)
}
