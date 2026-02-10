package org.blindkey.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class FireStoreResponse(
    val content: String,
    val hasPunctuation: Boolean,
    val language: String,
    val length: Int,
    val random: Double,
    val wordsCount: Int
)