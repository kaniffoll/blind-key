package org.blindkey.app.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import blind_key.sharedui.generated.resources.*
import org.blindkey.app.components.TextDropdownMenu
import org.blindkey.app.components.TopBar
import org.blindkey.app.model.IconInfo
import org.blindkey.app.res.Dimens
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Settings(
    viewModel: SettingsViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val theme by viewModel.theme.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(Dimens.medium),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(Dimens.small2)
    ) {
        TopBar(
            icons = arrayOf(
                IconInfo(
                    drawableResource = Res.drawable.arrow_back_24px,
                    stringResource = Res.string.arrow_back_icon,
                )
            )
        ) {
            onBack()
        }

        Text(
            text = stringResource(Res.string.settings_title),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimens.small2),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("${stringResource(Res.string.theme)}:")
            TextDropdownMenu(initValue = theme.toString(), "light", "dark", "system") {
                viewModel.changeTheme(it)
            }
        }

        OutlinedButton(onClick = { viewModel.updateLocalData() }) {
            Text(stringResource(Res.string.update_local))
        }

        OutlinedButton(onClick = { viewModel.openGitHub() }) {
            Text(stringResource(Res.string.open_github))
        }
    }
}