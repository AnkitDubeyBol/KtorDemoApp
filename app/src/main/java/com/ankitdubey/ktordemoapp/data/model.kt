package com.ankitdubey.ktordemoapp.data

import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Serializable
data class RandomUserDao(
    val info: Info
)

@Serializable
data class Info(
    val results: Int,
    val page: Int,
    val version: String
)


infix fun Date.toDateStr(format: String): String {
    return try {
        val sdf = SimpleDateFormat(format, Locale.ENGLISH)
        sdf.timeZone = TimeZone.getTimeZone("Asia/Dubai")
        sdf.format(this)
    } catch (ex: Exception) {
        ""
    }
}