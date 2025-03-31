/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 2025/3/31
 */

@file:Suppress("unused")

package cn.rtast.rkmbed.gradle

import org.intellij.lang.annotations.Language

fun StringBuilder.appendCode(@Language("kotlin") code: String) = appendLine(code)