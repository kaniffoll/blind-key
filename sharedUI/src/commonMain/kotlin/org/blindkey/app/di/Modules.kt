package org.blindkey.app.di

import org.blindkey.app.screens.settings.SettingsViewModel
import org.blindkey.app.screens.test.TestViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<TestViewModel> { TestViewModel(get(), get()) }
    viewModel<SettingsViewModel> { SettingsViewModel(get()) }
}