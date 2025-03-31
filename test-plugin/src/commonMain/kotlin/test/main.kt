/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package test

import cn.rtast.rkmbed.runtime.RKMBedResource
import cn.rtast.rkmbed.runtime.getResource
import cn.rtast.test.resources.GeneratedResource

fun main() {
    RKMBedResource.setResource(GeneratedResource)
    println(getResource("a.text").asString())
}