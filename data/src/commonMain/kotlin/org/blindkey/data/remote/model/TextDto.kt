package org.blindkey.data.remote.model

data class TextDto(
    val documentId: String,
    val content: String,
    val hasPunctuation: Boolean,
    val language: String,
    val length: Int,
    val random: Double,
    val wordsCount: Int
)
