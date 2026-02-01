package org.blindkey.data.local

import org.blindkey.domain.model.Text

fun TextEntity.toText() = Text (
    content,
    hasPunctuation,
    language,
    length
)