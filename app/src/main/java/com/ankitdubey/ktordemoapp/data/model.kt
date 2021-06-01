package com.ankitdubey.ktordemoapp.data

import kotlinx.serialization.Serializable

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
