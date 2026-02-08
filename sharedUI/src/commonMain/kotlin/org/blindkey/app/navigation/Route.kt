package org.blindkey.app.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route: NavKey {
    @Serializable
    data object Settings: Route, NavKey
    @Serializable
    data object Test: Route, NavKey

    @Serializable
    data object Result: Route, NavKey
}