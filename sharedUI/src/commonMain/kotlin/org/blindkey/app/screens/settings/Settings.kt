package org.blindkey.app.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import blind_key.sharedui.generated.resources.Res
import blind_key.sharedui.generated.resources.arrow_back_24px
import blind_key.sharedui.generated.resources.arrow_back_icon
import org.blindkey.app.components.TopBar
import org.blindkey.app.model.IconInfo

@Composable
fun Settings(onBack: () -> Unit) {
    TopBar(
        icons = arrayOf(
            IconInfo(
                drawableResource = Res.drawable.arrow_back_24px,
                stringResource = Res.string.arrow_back_icon,
            )
        )
    ) {
        onBack()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Settings")
    }
}