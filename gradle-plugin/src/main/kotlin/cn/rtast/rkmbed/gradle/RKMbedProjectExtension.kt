/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

package cn.rtast.rkmbed.gradle

import cn.rtast.rkmbed.gradle.target.KMPTarget
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.jetbrains.kotlin.gradle.plugin.KotlinTarget

abstract class RKMbedProjectExtension {
    /**
     * 配置生成的资源类的包名
     */
    @get:Input
    abstract val packageName: Property<String>

    /**
     * 配置所有的resources文件夹, 这个选项和[mappedResourcesPath]冲突
     */
    @get:Input
    abstract val resourcePath: ListProperty<String>

    /**
     * 配置映射的资源文件夹
     */
    @get:Input
    abstract val mappedResourcesPath: MapProperty<String, KMPTarget>

    /**
     * 是否合并所有配置的资源文件夹()到generated/kotlin/内作为commonMain的sourceSet, 让所有的源码集都可以访问
     */
    @get:Input
    abstract val mergeResources: Property<Boolean>

    init {
        resourcePath.set(mutableListOf("commonMain/resources"))
        mappedResourcesPath.set(mapOf())
        mergeResources.convention(true)
    }
}