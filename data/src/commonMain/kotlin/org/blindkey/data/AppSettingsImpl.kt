package org.blindkey.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import org.blindkey.data.datastore.SettingsKeys
import org.blindkey.domain.model.ThemeParam
import org.blindkey.domain.repo.TextRepository
import org.blindkey.domain.settings.AppSettings

class AppSettingsImpl(
    private val dataStore: DataStore<Preferences>,
    private val textRepository: TextRepository,
) : AppSettings {
    override val theme: Flow<ThemeParam> =
        dataStore.data.map { preferences ->
            preferences[SettingsKeys.THEME_PARAM]
                ?.let {
                    Json.decodeFromString(ThemeParam.serializer(), it)
                }?: ThemeParam.System
        }

    override suspend fun setTheme(theme: ThemeParam) {
        val json = Json.encodeToString(ThemeParam.serializer(), theme)
        dataStore.edit { preferences ->
            preferences[SettingsKeys.THEME_PARAM] = json
        }
    }

    override suspend fun updateLocalData() {
        textRepository.initDatabase(true)
    }
}