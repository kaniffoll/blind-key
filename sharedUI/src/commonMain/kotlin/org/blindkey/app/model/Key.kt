package org.blindkey.app.model

sealed class Key(open val key: Char) {
    class OkNoted(override val key: Char): Key(key)
    class ErrNoted(override val key: Char): Key(key)
}