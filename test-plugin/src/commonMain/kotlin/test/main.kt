/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package test

import cn.rtast.test.resources.getResources
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

fun main() {
    println(getResources("a.text").asString())
}

public class AbstractResource(private val uSource: UByteArray) {

    private val source = uSource.toByteArray()

    public fun asByteArray(): ByteArray {
        return source
    }

    public fun asString(): String {
        return source.decodeToString()
    }

    public fun saveTo(path: Path) {
        SystemFileSystem.sink(path).use { it.buffered().write(source) }
    }
}