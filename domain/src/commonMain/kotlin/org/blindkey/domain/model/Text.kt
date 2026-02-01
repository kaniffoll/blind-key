package org.blindkey.domain.model

data class Text(
    val content: String,
    val hasPunctuation: Boolean,
    val language: String,
    val length: Int,
)
