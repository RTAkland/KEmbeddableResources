/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.kembeddable.resources.gradle

import cn.rtast.kembeddable.resources.gradle.util.CommonMain
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import java.io.File

abstract class KEmbeddableResourcesExtension {
    @get:Input
    abstract val resourcePath: ListProperty<File>

    @get:Input
    abstract val compression: Property<Boolean>

    @get:Input
    abstract val publicGeneratedResourceVariable: Property<Boolean>

    @get:Input
    abstract val packageName: Property<String>

    @get:Input
    abstract val maxSingleFileSize: Property<Int>

    init {
        resourcePath.convention(listOf(CommonMain))
        compression.convention(false)
        publicGeneratedResourceVariable.convention(false)
        packageName.convention("common")
        maxSingleFileSize.convention(64)
    }
}