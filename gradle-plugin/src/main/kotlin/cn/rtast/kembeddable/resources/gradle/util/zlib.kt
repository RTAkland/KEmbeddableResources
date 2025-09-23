/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.kembeddable.resources.gradle.util

import java.io.ByteArrayOutputStream
import java.util.zip.Deflater
import java.util.zip.DeflaterOutputStream

fun ByteArray.deflate(): ByteArray {
    val baos = ByteArrayOutputStream()
    val dos = DeflaterOutputStream(baos)
    dos.write(this)
    dos.close()
    return baos.toByteArray()
}
