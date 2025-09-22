/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.kembeddable.resources.gradle

import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input

abstract class KEmbeddableResourcesExtension {
    @get:Input
    abstract val resourcePath: MapProperty<String, String>

    @get:Input
    abstract val compression: Property<Boolean>

    init {
        resourcePath.convention(mapOf("commonMain/resources" to "example.common"))
        compression.convention(false)
    }
}