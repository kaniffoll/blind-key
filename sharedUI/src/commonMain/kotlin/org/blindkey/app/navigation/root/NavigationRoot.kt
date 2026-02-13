package org.blindkey.app.navigation.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.blindkey.app.navigation.Route
import org.blindkey.app.navigation.main.NavigationMain
import org.blindkey.app.ui.settings.Settings

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val rootBackStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.Settings::class, Route.Settings.serializer())
                    subclass(Route.Main::class, Route.Main.serializer())
                }
            }
        },
        Route.Main
    )

    NavDisplay(
        modifier = modifier,
        backStack = rootBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            entry<Route.Settings> {
                Settings {
                    if (rootBackStack.size > 1)
                        rootBackStack.removeLast()
                }
            }
            entry<Route.Main> {
                NavigationMain {
                    rootBackStack.add(it)
                }
            }
        }
    )
}