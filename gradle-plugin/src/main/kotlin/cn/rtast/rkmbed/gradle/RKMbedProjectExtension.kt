/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.rkmbed.gradle

import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input

abstract class RKMbedProjectExtension {
    @get:Input
    abstract val packageName: Property<String>
}