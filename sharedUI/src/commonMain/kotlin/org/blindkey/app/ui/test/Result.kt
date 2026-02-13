package org.blindkey.app.ui.test

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
import org.blindkey.app.ui.components.ErrorGraph
import org.blindkey.app.model.TestResult
import org.blindkey.app.res.Dimens
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Result(
    viewModel: MainViewModel,
    onBack: () -> Unit
) {
    val testResult = viewModel.getTestResult()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ErrorGraph(testResult.errorList)

        Spacer(modifier = Modifier.height(Dimens.medium))

        Info(testResult)

        Spacer(modifier = Modifier.height(Dimens.medium))

        ButtonColumn(viewModel, onBack)
    }
}

@Composable
private fun ButtonColumn(
    viewModel: MainViewModel,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextAndButton(
            painter = painterResource(Res.drawable.refresh_24px),
            contentDescription = stringResource(Res.string.refresh_icon),
            text = stringResource(Res.string.restart_testing),
        ) {
            viewModel.reset()
            onBack()
        }

        TextAndButton(
            painter = painterResource(Res.drawable.arrow_forward_24px),
            contentDescription = stringResource(Res.string.arrow_forward_icon),
            text = stringResource(Res.string.next_text),
        ) {
            viewModel.getNewText()
            onBack()
        }
    }
}

@Composable
private fun Info(testResult: TestResult) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(Dimens.large)) {
        val style = MaterialTheme.typography.displaySmall

        Text(
            text = "${stringResource(Res.string.wpm)} ${testResult.wpm}",
            style = style
        )
        Text(
            text = "${stringResource(Res.string.accuracy)} ${testResult.accuracy}%",
            style = style
        )
    }
}

@Composable
private fun TextAndButton(painter: Painter, contentDescription: String, text: String, onClick: () -> Unit) {

    Row(
        modifier = Modifier.width(Dimens.textAndButtonWidth),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text,
            style = MaterialTheme.typography.displaySmall,
        )

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