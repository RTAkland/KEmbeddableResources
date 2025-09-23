/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

@file:OptIn(ExperimentalUnsignedTypes::class, ExperimentalEncodingApi::class)

package cn.rtast.kembeddable.resources.runtime

import cn.rtast.kzlib.zlibDecompress
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

public class Resource(bSource: List<String>, private val compressed: Boolean = false) {
    private val source = bSource.map { Base64.decode(it) }.merge()
    public fun asByteArray(): ByteArray = if (compressed) source.zlibDecompress() else source
    public fun asString(): String = asByteArray().decodeToString()

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
}