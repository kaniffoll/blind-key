package org.blindkey.app.model

import androidx.navigation3.runtime.NavKey
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class IconInfo(
    val drawableResource: DrawableResource,
    val stringResource: StringResource,
    val route: NavKey? = null
)
