package org.blindkey.app.ui.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.zIndex
import androidx.navigation3.runtime.NavKey
import blind_key.sharedui.generated.resources.*
import org.blindkey.app.model.IconInfo
import org.blindkey.app.navigation.Route
import org.blindkey.app.res.Dimens
import org.blindkey.app.ui.components.LessonText
import org.blindkey.app.ui.components.TestParams
import org.blindkey.app.ui.components.TopBar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Test(
    viewModel: MainViewModel,
    navigateTo: (NavKey) -> Unit
) {
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        TopContent(viewModel = viewModel, navigateTo = navigateTo)
        MainContent(viewModel = viewModel)
    }
}


@Composable
private fun MainContent(viewModel: MainViewModel) {
    val interactionSource = remember { MutableInteractionSource() }
    val currentText by viewModel.currentText.collectAsState()
    val typedKeyList by viewModel.typedKeyList.collectAsState()
    val dumpText by viewModel.dumpText.collectAsState()

    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    TextField(
        modifier = Modifier.size(Dimens.zero).focusRequester(focusRequester)
            .onFocusChanged { isFocused = it.isFocused },
        value = dumpText,
        onValueChange = viewModel::onTextChange,
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(0f)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                viewModel.reset()
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            currentText?.let {
                LessonText(
                    text = it.content,
                    typedKeyList = typedKeyList,
                    isFocused = isFocused,
                    focusRequester = focusRequester,
                )
            } ?: Text(stringResource(Res.string.no_content))

            OutlinedButton(onClick = { viewModel.getNewText() }) {
                Image(
                    painterResource(Res.drawable.refresh_24px),
                    stringResource(Res.string.refresh_icon),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
                )
            }
        }
    }
}

@Composable
private fun TopContent(viewModel: MainViewModel, navigateTo: (NavKey) -> Unit) {
    val isTestFinished by viewModel.isTestFinished.collectAsState()
    val testParam by viewModel.testParam.collectAsState()

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

        TestParams(testParam, onChange = viewModel::changeTestParam)
    }
}