/*
 * Copyright © 2025 RTAkland
 * Author: RTAkland
 * Date: 9/23/25
 */


package cn.rtast.kembeddable.resources.gradle.util

import java.util.*

val ByteArray.base64: String get() = Base64.getEncoder().encodeToString(this)

val String.decodeBase64: ByteArray get() = Base64.getDecoder().decode(this)