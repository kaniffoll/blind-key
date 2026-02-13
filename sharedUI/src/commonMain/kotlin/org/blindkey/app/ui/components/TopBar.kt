package org.blindkey.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.navigation3.runtime.NavKey
import org.blindkey.app.model.IconInfo
import org.blindkey.app.res.Dimens
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopBar(modifier: Modifier = Modifier, vararg icons: IconInfo, onClick: (NavKey?) -> Unit) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        icons.forEach { icon ->
            Image(
                painter = painterResource(
                    resource = icon.drawableResource,
                ),
                contentDescription = stringResource(icon.stringResource),
                modifier = Modifier.size(Dimens.iconSize).clickable {
                    onClick(icon.route)
                },
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary)
            )
        }
    }
}