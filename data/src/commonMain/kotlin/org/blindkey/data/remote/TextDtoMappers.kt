package org.blindkey.data.remote

import org.blindkey.data.local.TextEntity
import org.blindkey.domain.model.Text

fun FireStoreResponse.toText() = Text(
    content,
    hasPunctuation,
    language,
    length
)

fun FireStoreResponse.toTextDto(documentId: String) = TextDto(
    documentId = documentId,
    content = content,
    hasPunctuation = hasPunctuation,
    language = language,
    length = length,
    random = random
)

fun TextDto.toEntity() = TextEntity(
    id = documentId,
    content = content,
    hasPunctuation = hasPunctuation,
    language = language,
    length = length,
    random = random
)