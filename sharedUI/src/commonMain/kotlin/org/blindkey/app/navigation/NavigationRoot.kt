package org.blindkey.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.blindkey.app.screens.result.Result
import org.blindkey.app.screens.settings.Settings
import org.blindkey.app.screens.test.Test

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.Settings::class, Route.Settings.serializer())
                    subclass(Route.Test::class, Route.Test.serializer())
                    subclass(Route.Result::class, Route.Result.serializer())
                }
            }
        },
        Route.Test
    )

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryProvider = { key ->
            when (key) {
                is Route.Test -> {
                    NavEntry(key) {
                        Test {
                            backStack.add(it)
                        }
                    }
                }
                is Route.Settings -> {
                    NavEntry(key) {
                        Settings {
                            if (backStack.size > 1)
                                backStack.removeLast()
                        }
                    }
                }
                is Route.Result -> {
                    NavEntry(key) {
                        Result {
                            if (backStack.size > 1)
                                backStack.removeLast()
                        }
                    }
                }
                else -> error("Unsupported key $key")
            }
        }
    )
}