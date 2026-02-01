package org.blindkey.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class TextDto(
    val content: String,
    val hasPunctuation: Boolean,
    val language: String,
    val length: Int,
    val random: Double
)