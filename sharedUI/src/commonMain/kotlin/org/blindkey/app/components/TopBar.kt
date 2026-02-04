package org.blindkey.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.navigation3.runtime.NavKey
import blind_key.sharedui.generated.resources.Res
import blind_key.sharedui.generated.resources.settings_icon
import org.blindkey.app.model.IconInfo
import org.blindkey.app.res.Dimens
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopBar(modifier: Modifier = Modifier, vararg icons: IconInfo, onClick: (NavKey?) -> Unit) {
    Row(modifier = modifier.fillMaxHeight().padding(Dimens.medium), horizontalArrangement = Arrangement.SpaceBetween) {
        icons.forEach { icon ->
            Image(
                painter = painterResource(
                    resource = icon.drawableResource,
                ),
                contentDescription = stringResource(icon.stringResource),
                modifier = Modifier.size(Dimens.iconSize).clickable {
                    onClick(icon.route)
                },
                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
            )
        }
    }
}