package org.blindkey.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface ThemeParam {
    @Serializable
    @SerialName("light")
    data object Light: ThemeParam
    @Serializable
    @SerialName("dark")
    data object Dark: ThemeParam
    @Serializable
    @SerialName("system")
    data object System: ThemeParam
}