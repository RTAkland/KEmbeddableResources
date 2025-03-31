/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.rkmbed.runtime

public fun main() {
    val bytes = byteArrayOf()
    bytes.decodeToString()
}

public class RResource {
    public val res: Map<String, AbstractResource> = mapOf<String, AbstractResource>(
        "" to AbstractResource(byteArrayOf()),
    )

    public operator fun get(path: String): AbstractResource {
        return requireNotNull(res[path]) { "资源不存在!" }
    }
}

public class AbstractResource(private val source: ByteArray) {
    public fun asByteArray(): ByteArray {
        return source
    }

    public fun asString(): String {
        return source.decodeToString()
    }
}