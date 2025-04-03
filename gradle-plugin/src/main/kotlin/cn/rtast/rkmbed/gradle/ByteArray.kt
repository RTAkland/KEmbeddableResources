/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.rkmbed.gradle

import cn.rtast.rkmbed.runtime.zlibCompress
import java.io.File

fun File.toUByteArrayOf(compress: Boolean = false): String {
    val byteArray = this.readBytes()
    val finalByteArray = if (compress) byteArray.zlibCompress() else byteArray
    val hexBytes = finalByteArray.joinToString(", ") { "0x%02XU".format(it.toInt() and 0xFF) }
    return "ubyteArrayOf($hexBytes)"
}