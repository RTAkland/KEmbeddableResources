/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/4/3
 */

package cn.rtast.kembeddable.resources.runtime

import kotlinx.io.Buffer
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

/**
 * 通过kotlinx-io的Path写入文件
 */
public fun Resource.saveTo(path: Path) {
    val buffer = Buffer().apply { write(asByteArray()) }
    SystemFileSystem.sink(path).use { it.write(buffer, buffer.size) }
}