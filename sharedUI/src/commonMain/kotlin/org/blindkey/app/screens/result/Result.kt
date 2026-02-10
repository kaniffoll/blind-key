package org.blindkey.app.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import blind_key.sharedui.generated.resources.*
import org.blindkey.app.components.ErrorGraph
import org.blindkey.app.screens.test.MainViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Result(
    viewModel: MainViewModel,
    onBack: () -> Unit
) {
    val textParam = viewModel.getTestResult()

    Column(modifier = Modifier.fillMaxSize()) {
        ErrorGraph(textParam.errorList)

        Text(textParam.wpm.toString())

        ButtonRow(viewModel, onBack)
    }
}

@Composable
private fun ButtonRow(
    viewModel: MainViewModel,
    onBack: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedButton(
            onClick = {
                viewModel.resetTypedKeyList()
                onBack()
            }
        ) {
            Image(
                painter = painterResource(Res.drawable.refresh_24px),
                contentDescription = stringResource(Res.string.refresh_icon)
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
                contentDescription = stringResource(Res.string.arrow_forward_icon)
            )
        }
    }
}