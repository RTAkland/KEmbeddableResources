/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 9/23/25
 */


package cn.rtast.kembeddable.resources.gradle.util

import org.gradle.api.Project
import org.gradle.api.file.Directory
import org.gradle.api.provider.Provider

fun Project.buildDir(path: String): Provider<Directory?> = this.layout.buildDirectory.dir(path)

fun Project.workDir(path: String): Directory = this.layout.projectDirectory.dir(path)