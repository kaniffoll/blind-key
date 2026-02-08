package org.blindkey.domain.model

import org.blindkey.domain.utils.toInt

data class TestParam(
    val hasPunctuation: Boolean? = null,
    val language: Language? = null,
    val length: Length? = null,
) {
    fun forEachNotNull(block: (String, Any) -> Unit) {
        hasPunctuation?.let { block(HAS_PUNCTUATION_NAME, it.toInt()) }
        language?.let { block(LANGUAGE_NAME, it.value) }
        length?.let { block(LENGTH_NAME, it.value) }
    }

    fun hasPunctuationAsString(): String {
        return when (hasPunctuation) {
            null -> ALL_STR
            true -> HAS_PUNCTUATION_YES
            false -> HAS_PUNCTUATION_NO
        }
    }

    fun languageAsString(): String {
        return when (language) {
            null -> ALL_STR
            Language.RUSSIAN -> Language.ENGLISH.value
            Language.ENGLISH -> Language.RUSSIAN.value
        }
    }

    fun lengthAsString(): String {
        return when (length) {
            null -> ALL_STR
            Length.Small -> Length.SMALL_NAME
            Length.Medium -> Length.MEDIUM_NAME
            Length.Large -> Length.LARGE_NAME
        }
    }

    companion object {
        const val HAS_PUNCTUATION_NAME = "hasPunctuation"
        const val LANGUAGE_NAME = "language"
        const val LENGTH_NAME = "length"

        const val ALL_STR = "all"
        const val HAS_PUNCTUATION_YES = "yes"
        const val HAS_PUNCTUATION_NO = "no"


        fun getHasPunctuationByStringValue(strValue: String): Boolean? {
            return when (strValue) {
                ALL_STR -> null
                HAS_PUNCTUATION_YES -> true
                HAS_PUNCTUATION_NO -> false
                else -> throw IllegalArgumentException("Wrong string argument: $strValue")
            }
        }

        fun getLanguageByStringValue(strValue: String): Language? {
            return when (strValue) {
                ALL_STR -> null
                Language.RUSSIAN.value -> Language.RUSSIAN
                Language.ENGLISH.value -> Language.ENGLISH
                else -> throw IllegalArgumentException("Wrong string argument: $strValue")
            }
        }

        fun getLengthByStringValue(strValue: String): Length? {
            return when (strValue) {
                ALL_STR -> null
                Length.SMALL_NAME -> Length.Small
                Length.MEDIUM_NAME -> Length.Medium
                Length.LARGE_NAME -> Length.Large
                else -> throw IllegalArgumentException("Wrong string argument: $strValue")
            }
        }
    }
}