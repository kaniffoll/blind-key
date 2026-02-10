package org.blindkey.app.screens.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.zIndex
import androidx.navigation3.runtime.NavKey
import blind_key.sharedui.generated.resources.*
import org.blindkey.app.components.LessonText
import org.blindkey.app.components.TestParams
import org.blindkey.app.components.TopBar
import org.blindkey.app.model.IconInfo
import org.blindkey.app.navigation.Route
import org.blindkey.app.res.Dimens
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Test(
    viewModel: MainViewModel,
    navigateTo: (NavKey) -> Unit
) {
    TopContent(viewModel = viewModel, navigateTo = navigateTo)
    MainContent(viewModel = viewModel)
}


@Composable
private fun MainContent(viewModel: MainViewModel) {
    val interactionSource = remember { MutableInteractionSource() }
    val currentText by viewModel.currentText.collectAsState()
    val typedKeyList by viewModel.typedKeyList.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(0f)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { viewModel.resetTypedKeyList() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LessonText(currentText.content, typedKeyList) { key ->
                viewModel.checkKey(key)
            }
            OutlinedButton(onClick = { viewModel.getNewText() }) {
                Image(
                    painterResource(Res.drawable.refresh_24px),
                    stringResource(Res.string.refresh_icon),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
                )
            }
//        Button(onClick = { viewModel.addText() }) {}
        }
    }
}

@Composable
private fun TopContent(viewModel: MainViewModel, navigateTo: (NavKey) -> Unit) {
    val isTestFinished by viewModel.isTestFinished.collectAsState()

    LaunchedEffect(isTestFinished) {
        if (isTestFinished) {
            navigateTo(Route.Main.Result)
        }
    }

    Column(modifier = Modifier.fillMaxWidth().zIndex(1f)) {
        TopBar(
            modifier = Modifier.padding(Dimens.medium),
            icons = arrayOf(
                IconInfo(
                    drawableResource = Res.drawable.settings_24px,
                    stringResource = Res.string.settings_icon,
                    route = Route.Settings
                )
            )
        ) {
            navigateTo(it ?: throw Exception("Unknown Navigation Key"))
        }

        TestParams(
            onHasPunctuationChange = { viewModel.changeHasPunctuation(hasPunctuationAsString = it) },
            onLanguageChange = { viewModel.changeLanguage(it) },
            onLengthChange = { viewModel.changeLength(it) },
        )
    }
}