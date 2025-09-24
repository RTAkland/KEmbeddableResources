@file:OptIn(ExperimentalEncodingApi::class)

import cn.rtast.kembeddable.resources.runtime.saveTo
import cn.rtast.kembeddable.resources.runtime.suspended.asByteArrayFlow
import common.getResource
import kotlinx.coroutines.runBlocking
import kotlinx.io.Buffer
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlin.io.encoding.ExperimentalEncodingApi

/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 9/23/25
 */


fun main() {
    runBlocking {
        val bytes = getResource("krepo-backend.js").asByteArrayFlow()
        bytes.collect {
            println(it.size)
        }
    }
//    val buffer = Buffer().apply { write(bytes) }
//    SystemFileSystem.sink(Path("krepo-backend.js")).use { it.write(buffer, buffer.size) }
}