/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.kembeddable.resources.gradle.util

import java.io.ByteArrayOutputStream
import java.util.zip.Deflater

fun ByteArray.deflate(): ByteArray {
    val deflater = Deflater()
    deflater.setInput(this)
    deflater.finish()
    val output = ByteArrayOutputStream()
    val buffer = ByteArray(1024)
    while (!deflater.finished()) {
        val count = deflater.deflate(buffer)
        output.write(buffer, 0, count)
    }
    deflater.end()
    return output.toByteArray()
}
