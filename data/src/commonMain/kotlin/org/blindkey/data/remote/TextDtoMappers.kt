package org.blindkey.data.remote

import org.blindkey.data.local.model.TextEntity
import org.blindkey.data.remote.model.FireStoreResponse
import org.blindkey.data.remote.model.TextDto

fun FireStoreResponse.toTextDto(documentId: String) = TextDto(
    documentId = documentId,
    content = content,
    hasPunctuation = hasPunctuation,
    language = language,
    length = length,
    random = random,
    wordsCount = wordsCount,
)

fun TextDto.toEntity() = TextEntity(
    id = documentId,
    content = content,
    hasPunctuation = hasPunctuation,
    language = language,
    length = length,
    random = random,
    wordsCount = wordsCount,
)