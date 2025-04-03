/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

@file:OptIn(ExperimentalUnsignedTypes::class)

package cn.rtast.rkmbed.runtime

import kotlinx.io.Buffer
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

public class Resource(uSource: UByteArray, private val compressed: Boolean = false) {
    private val source = uSource.toByteArray()

    public fun asByteArray(): ByteArray = source

    public fun asString(): String {
        return asByteArray().decodeToString()
    }

    public fun saveTo(path: Path) {
        val buffer = Buffer().apply { write(asByteArray()) }
        SystemFileSystem.sink(path).use { it.write(buffer, buffer.size) }
    }
}