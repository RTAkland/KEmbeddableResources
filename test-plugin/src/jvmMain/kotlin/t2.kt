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
    krepo_backend_js_6,
    krepo_backend_js_7,
    krepo_backend_js_8,
    krepo_backend_js_9,
    krepo_backend_js_10,
    krepo_backend_js_11,
    krepo_backend_js_12,
    krepo_backend_js_13,
    krepo_backend_js_14,
    krepo_backend_js_15,
    krepo_backend_js_16,
    krepo_backend_js_17,
    krepo_backend_js_18,
    krepo_backend_js_19,
    krepo_backend_js_20,
    krepo_backend_js_21,
    krepo_backend_js_22,
    krepo_backend_js_23,
    krepo_backend_js_24,
    krepo_backend_js_25,
    krepo_backend_js_26,
    krepo_backend_js_27,
    krepo_backend_js_28,
    krepo_backend_js_29,
    krepo_backend_js_30,
    krepo_backend_js_31,
    krepo_backend_js_32,
    krepo_backend_js_33,
    krepo_backend_js_34,
    krepo_backend_js_35,
    krepo_backend_js_36,
    krepo_backend_js_37,
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
