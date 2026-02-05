package org.blindkey.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ThemeParam {
    @Serializable
    @SerialName("light")
    data object Light: ThemeParam()
    @Serializable
    @SerialName("dark")
    data object Dark: ThemeParam()
    @Serializable
    @SerialName("system")
    data object System: ThemeParam()

    override fun toString(): String {
        return when(this) {
            Dark -> "dark"
            Light -> "light"
            System -> "system"
        }
    }
}