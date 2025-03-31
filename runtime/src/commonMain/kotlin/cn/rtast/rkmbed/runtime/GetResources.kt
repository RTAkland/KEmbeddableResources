/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

@file:Suppress("VARIABLES_NAME", "unused")

package cn.rtast.rkmbed.runtime

private var internalRes = mapOf<String, Resource>()

public object RKMBedResource {
    public fun setResource(list: Map<String, Resource>) {
        internalRes = list
    }
}

public fun getResource(path: String): Resource {
    return requireNotNull(internalRes[path]) { "资源 $path 不存在! | Resource $path is not exists!" }
}