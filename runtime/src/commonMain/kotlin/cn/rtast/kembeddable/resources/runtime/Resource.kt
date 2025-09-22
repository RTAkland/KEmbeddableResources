/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

@file:OptIn(ExperimentalUnsignedTypes::class)

package cn.rtast.kembeddable.resources.runtime

import cn.rtast.kzlib.zlibDecompress

public class Resource(uSource: UByteArray, private val compressed: Boolean = false) {
    private val source = uSource.toByteArray()
    public fun asByteArray(): ByteArray = if (compressed) source.zlibDecompress() else source
    public fun asString(): String {
        return asByteArray().decodeToString()
    }
}