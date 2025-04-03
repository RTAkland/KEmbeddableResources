/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

@file:OptIn(ExperimentalUnsignedTypes::class)

package cn.rtast.rkmbed.runtime

public class Resource(uSource: UByteArray, private val compressed: Boolean = false) {
    private val source = uSource.toByteArray()

    public fun asByteArray(): ByteArray = source

    public fun asString(): String {
        return asByteArray().decodeToString()
    }
}