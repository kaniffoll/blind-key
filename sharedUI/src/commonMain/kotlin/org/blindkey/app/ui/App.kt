package org.blindkey.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.blindkey.app.components.LessonText
import org.blindkey.app.theme.AppTheme
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
fun App(
    onThemeChanged: @Composable (isDark: Boolean) -> Unit = {}
) = AppTheme(onThemeChanged) {
    val viewModel = koinViewModel<AppViewModel>()

    val currentText by viewModel.currentText.collectAsState()
    val typedKeyList by viewModel.typedKeyList.collectAsState()
    val focusRequester = FocusRequester()
    //val logger = Logger.withTag("App")

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LessonText(currentText, typedKeyList) {
            focusRequester.requestFocus()
        }
    }

    TextField(
        value = "",
        onValueChange = {
            viewModel.checkKey(it.last())
        },
        modifier = Modifier.size(0.dp).focusRequester(focusRequester),
    )
}
