package org.blindkey.app.model

sealed class Key(open val key: Char) {
    class OkNoted(override val key: Char): Key(key)
    class ErrNoted(override val key: Char): Key(key)

    override fun toString(): String {
        return when (this) {
            is OkNoted -> "OK: $key"
            is ErrNoted -> "ERR: $key"
        }
    }
}