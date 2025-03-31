/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.rkmbed.gradle

import java.io.File

fun File.toByteArrayOf(): String {
    val bytes = this.readBytes()
    val hexBytes = bytes.joinToString(", ") { "0x${it.toString(16).padStart(2, '0').uppercase()}" }
    return "byteArrayOf($hexBytes)"
}