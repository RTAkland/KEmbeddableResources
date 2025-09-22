/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

@file:Suppress("unused")

package cn.rtast.kembeddable.resources.gradle.util

import org.intellij.lang.annotations.Language

fun StringBuilder.appendCode(@Language("Kotlin") code: String) = appendLine(code)

fun StringBuilder.appendLine(str: String, count: Int) = repeat(count) { this@appendLine.appendLine(str) }