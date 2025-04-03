/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/4/3
 */


@file:OptIn(ExperimentalForeignApi::class)

package cn.rtast.rkmbed.runtime

import cn.rtast.zlibc.compress_data
import cn.rtast.zlibc.decompress_data
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.refTo

public actual fun ByteArray.zlibCompress(): ByteArray {
    val output = ByteArray(this.size * 2)
    return memScoped {
        val inputRef = this@zlibCompress.toUByteArray().refTo(0)
        val outputRef = output.toUByteArray().refTo(0)
        val decompressedSize =
            decompress_data(inputRef, this@zlibCompress.size.toULong(), outputRef, output.size.toULong())
        output.copyOf(decompressedSize)
    }
}

public actual fun ByteArray.zlibDecompress(): ByteArray {
    val output = ByteArray(this.size * 2)
    return memScoped {
        val inputRef = this@zlibDecompress.toUByteArray().refTo(0)
        val outputRef = output.toUByteArray().refTo(0)
        val compressedSize =
            compress_data(inputRef, this@zlibDecompress.size.toULong(), outputRef, output.size.toULong())
        output.copyOf(compressedSize)
    }
}