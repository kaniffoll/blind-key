package org.blindkey.app.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.blindkey.app.navigation.NavigationRoot
import org.blindkey.app.theme.AppTheme

@Preview
@Composable
fun App() {
    AppTheme {
        NavigationRoot()
    }
}