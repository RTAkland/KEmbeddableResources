/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/4/3
 */


package cn.rtast.rkmbed.runtime

import java.util.zip.Deflater

public actual fun ByteArray.zlibDecompress(): ByteArray {
    TODO("Not yet implemented")
}

public actual fun ByteArray.zlibCompress(): ByteArray {
    val deflater = Deflater()
    deflater.setInput(this)
    deflater.finish()
    val output = ByteArray(1024)
    val compressedData = mutableListOf<Byte>()
    while (!deflater.finished()) {
        val compressedSize = deflater.deflate(output)
        compressedData.addAll(output.take(compressedSize))
    }
    deflater.end()
    return compressedData.toByteArray()
}