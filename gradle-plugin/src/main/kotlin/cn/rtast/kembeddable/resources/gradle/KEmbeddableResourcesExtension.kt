/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.kembeddable.resources.gradle

import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input

@Suppress("DEPRECATION")
abstract class KEmbeddableResourcesExtension {
    @get:Input
    abstract val packageName: Property<String>

    @get:Input
    abstract val resourcePath: ListProperty<String>

    @Deprecated("暂时不可用")
    @get:Input
    abstract val compression: Property<Boolean>

    init {
        resourcePath.set(mutableListOf("commonMain/resources"))
        compression.convention(true)
    }
}