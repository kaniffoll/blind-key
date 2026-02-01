package org.blindkey.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity("texts")
data class TextEntity(
    @PrimaryKey
    val id: String,
    val content: String,
    val hasPunctuation: Boolean,
    val language: String,
    val length: Int,
    val random: Double
)
