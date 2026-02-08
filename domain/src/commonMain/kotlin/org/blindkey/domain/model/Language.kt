package org.blindkey.domain.model

sealed interface Language {
    val value: String

    data object RUSSIAN: Language {
        override val value: String = "ru"
    }

    data object ENGLISH: Language {
        override val value: String = "en"
    }
}
