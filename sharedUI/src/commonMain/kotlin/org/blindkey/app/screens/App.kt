package org.blindkey.app.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import org.blindkey.app.navigation.NavigationRoot
import org.blindkey.app.screens.settings.SettingsViewModel
import org.blindkey.app.theme.AppTheme
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
fun App() {
    val viewModel = koinViewModel<SettingsViewModel>()
    val theme by viewModel.theme.collectAsState()
    AppTheme(theme) {
        NavigationRoot()
    }
}