package org.blindkey.app.screens.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import org.blindkey.app.components.LessonText
import org.blindkey.app.res.Dimens
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Test(navigateToSettings: () -> Unit) {
    val viewModel = koinViewModel<TestViewModel>()

    val currentText by viewModel.currentText.collectAsState()
    val typedKeyList by viewModel.typedKeyList.collectAsState()
    val focusRequester = FocusRequester()
    var isFocused by remember { mutableStateOf(false) }
    //val logger = Logger.withTag("App")

    LaunchedEffect(isFocused) {
        if (!isFocused) {
            viewModel.resetTypedKeyList()
        }
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
        Button(onClick = { viewModel.updateLocalData() }) {
            Text("Update")
        }
        Button(onClick = { viewModel.getNewText() }) {
            Text("Get new text")
        }
        Button(onClick = { navigateToSettings() }) {
            Text("Open Settings")
        }
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