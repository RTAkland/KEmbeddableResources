/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.kembeddable.resources.gradle

import java.io.ByteArrayOutputStream
import java.io.File
import java.util.zip.Deflater

fun File.toUByteArrayOf(compress: Boolean = false): String {
    val byteArray = this.readBytes()
    val finalBytes = if (compress) {
        val deflater = Deflater()
        deflater.setInput(byteArray)
        deflater.finish()
        val buffer = ByteArray(1024)
        val outputStream = ByteArrayOutputStream()
        while (!deflater.finished()) {
            val count = deflater.deflate(buffer)
            outputStream.write(buffer, 0, count)
        }
        deflater.end()
        outputStream.toByteArray()
    } else {
        byteArray
    }
    val hexBytes = finalBytes.joinToString(", ") { "0x%02XU".format(it.toInt() and 0xFF) }
    return "ubyteArrayOf($hexBytes)"
}
