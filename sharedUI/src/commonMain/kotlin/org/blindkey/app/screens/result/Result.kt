package org.blindkey.app.screens.result

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import blind_key.sharedui.generated.resources.Res
import blind_key.sharedui.generated.resources.arrow_back_24px
import blind_key.sharedui.generated.resources.arrow_back_icon
import org.blindkey.app.components.TopBar
import org.blindkey.app.model.IconInfo

@Composable
fun Result(onBack: () -> Unit) {
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

    Text("RESULT SCREEN")
}