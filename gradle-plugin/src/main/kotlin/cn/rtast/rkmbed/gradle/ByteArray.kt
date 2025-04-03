/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.rkmbed.gradle

import java.io.File

fun File.toUByteArrayOf(compress: Boolean = false): String {
    val byteArray = this.readBytes()
    val hexBytes = byteArray.joinToString(", ") { "0x%02XU".format(it.toInt() and 0xFF) }
    return "ubyteArrayOf($hexBytes)"
}