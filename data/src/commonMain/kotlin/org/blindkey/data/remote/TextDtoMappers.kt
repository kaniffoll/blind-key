package org.blindkey.data.remote

import org.blindkey.domain.model.Text

fun TextDto.toText() = Text (
    content,
    hasPunctuation,
    language,
    length
)