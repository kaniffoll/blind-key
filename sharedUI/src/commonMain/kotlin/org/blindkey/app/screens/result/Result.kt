package org.blindkey.app.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import blind_key.sharedui.generated.resources.*
import org.blindkey.app.components.ErrorGraph
import org.blindkey.app.res.Dimens
import org.blindkey.app.screens.test.MainViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Result(
    viewModel: MainViewModel,
    onBack: () -> Unit
) {
    val textParam = viewModel.getTestResult()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ErrorGraph(textParam.errorList)

        Text(textParam.wpm.toString())

        ButtonColumn(viewModel, onBack)
    }
}

@Composable
private fun ButtonColumn(
    viewModel: MainViewModel,
    onBack: () -> Unit
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedButton(
            onClick = {
                viewModel.resetTypedKeyList()
                onBack()
            }
        ) {
            Image(
                painter = painterResource(Res.drawable.refresh_24px),
                contentDescription = stringResource(Res.string.refresh_icon),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
            )
        }

        OutlinedButton(
            onClick = {
                viewModel.getNewText()
                onBack()
            }
        ) {
            Image(
                painter = painterResource(Res.drawable.arrow_forward_24px),
                contentDescription = stringResource(Res.string.arrow_forward_icon),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
            )
        }
    }
}


@Composable
private fun TextAndButton(painter: Painter, contentDescription: String, onClick: () -> Unit) {

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(Dimens.small)) {
        Text("")

        OutlinedButton(
            onClick = onClick,
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
            )
        }
    }

}