package org.blindkey.domain.model

sealed interface Length {
    val value: IntRange


    data object Small: Length {
        override val value: IntRange = SMALL_MIN..SMALL_MAX
    }

    data object Medium: Length {
        override val value: IntRange = MEDIUM_MIN..MEDIUM_MAX
    }

    data object Large: Length {
        override val value: IntRange = LARGE_MIN..LARGE_MAX
    }

    companion object {
        const val SMALL_MIN = 1
        const val MEDIUM_MIN = 121
        const val LARGE_MIN = 251

        const val LARGE_MAX = Int.MAX_VALUE
        const val MEDIUM_MAX = 250
        const val SMALL_MAX = 120

        const val SMALL_NAME = "small"
        const val MEDIUM_NAME = "medium"
        const val LARGE_NAME = "large"
    }
}