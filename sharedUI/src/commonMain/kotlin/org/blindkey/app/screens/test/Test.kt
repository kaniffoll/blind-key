package org.blindkey.app.screens.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.ColorFilter
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
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Test(navigateTo: (NavKey) -> Unit) {
    val viewModel = koinViewModel<TestViewModel>()

    val currentText by viewModel.currentText.collectAsState()
    val typedKeyList by viewModel.typedKeyList.collectAsState()
    val focusRequester = FocusRequester()
    var isFocused by remember { mutableStateOf(false) }
    //val logger = Logger.withTag("App")

    LaunchedEffect(viewModel.currentKey) {
        if (viewModel.currentKey == null) {
            navigateTo(Route.Result)
        }
    }

    LaunchedEffect(isFocused) {
        if (!isFocused) {
            viewModel.resetTypedKeyList()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
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

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LessonText(currentText, typedKeyList, isFocused) {
            focusRequester.requestFocus()
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

    TextField(
        value = "",
        onValueChange = {
            if (it.isNotEmpty()) {
                viewModel.checkKey(it.last())
            }
        },
        modifier = Modifier
            .size(Dimens.zero)
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocused = it.isFocused
            },
    )
}