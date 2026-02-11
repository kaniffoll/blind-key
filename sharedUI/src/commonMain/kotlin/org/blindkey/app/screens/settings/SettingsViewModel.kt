package org.blindkey.app.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.blindkey.domain.mappers.toThemeParam
import org.blindkey.domain.model.ThemeParam
import org.blindkey.domain.settings.AppSettings
import org.blindkey.domain.usecase.OpenUrlUseCase

class SettingsViewModel(
    private val settings: AppSettings,
    private val openUrlUseCase: OpenUrlUseCase,
): ViewModel() {
    val theme: StateFlow<ThemeParam> =
        settings.theme
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ThemeParam.System
            )

    fun changeTheme(param: String) {
        viewModelScope.launch(Dispatchers.Default) {
            settings.setTheme(param.toThemeParam())
        }
    }

    fun updateLocalData() {
        viewModelScope.launch(Dispatchers.Default) {
            settings.updateLocalData()
        }
    }

    fun openGitHub() = openUrlUseCase()
}