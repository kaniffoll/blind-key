package org.blindkey.app.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.blindkey.app.navigation.Route
import org.blindkey.app.screens.result.Result
import org.blindkey.app.screens.test.Test
import org.blindkey.app.screens.test.MainViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavigationMain(
    modifier: Modifier = Modifier,
    navigateToSettings: (NavKey) -> Unit
) {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.Main.Test::class, Route.Main.Test.serializer())
                    subclass(Route.Main.Result::class, Route.Main.Result.serializer())
                }
            }
        },
        Route.Main.Test
    )

    val viewModel = koinViewModel<MainViewModel>()

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        entryProvider = entryProvider {
            entry<Route.Main.Test> {
                Test(viewModel) {
                    if (it is Route.Settings)
                        navigateToSettings(it)
                    else
                        backStack.add(it)
                }
            }
            entry<Route.Main.Result> {
                Result(viewModel) {
                    if (backStack.size > 1) {
                        backStack.removeLast()
                    }
                }
            }
        }
    )
}