/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 9/22/25
 */


package cn.rtast.kembeddable.resources.gradle

import java.io.File

fun String.pathString(sep: String = "_"): String = this.replace(File.separator, sep)