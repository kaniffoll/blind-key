package org.blindkey.app.di

import org.blindkey.app.ui.AppViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<AppViewModel> { AppViewModel(get(), get()) }
}