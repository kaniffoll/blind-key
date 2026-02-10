package org.blindkey.data.local

import org.blindkey.data.local.model.TextEntity
import org.blindkey.domain.model.Text

fun TextEntity.toText() = Text (
    content,
    wordsCount,
)