package org.blindkey.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import org.blindkey.data.datastore.SettingsKeys
import org.blindkey.data.local.LocalDataSource
import org.blindkey.data.remote.RemoteDataSource
import org.blindkey.data.remote.toEntity
import org.blindkey.domain.model.TestParam
import org.blindkey.domain.model.ThemeParam
import org.blindkey.domain.settings.AppSettings

class AppSettingsImpl(
    private val dataStore: DataStore<Preferences>,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
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
        localDataSource.saveTexts(remoteDataSource.getAllTexts().map { it.toEntity() })
    }

    override suspend fun saveTestParameters(testParam: TestParam) {
        dataStore.edit { preferences ->
            preferences[SettingsKeys.HAS_PUNCTUATION_PARAM] = testParam.hasPunctuationAsString()
            preferences[SettingsKeys.LANGUAGE_PARAM] = testParam.languageAsString()
            preferences[SettingsKeys.LENGTH_PARAM] = testParam.lengthAsString()
        }
    }

    override suspend fun getTestParameters(): TestParam {
        val preferences = dataStore.data.first()

        return TestParam(
            hasPunctuation = preferences[SettingsKeys.HAS_PUNCTUATION_PARAM]
                ?.let { TestParam.getHasPunctuationByStringValue(it) },
            language = preferences[SettingsKeys.LANGUAGE_PARAM]
                ?.let { TestParam.getLanguageByStringValue(it) },
            length = preferences[SettingsKeys.LENGTH_PARAM]
                ?.let { TestParam.getLengthByStringValue(it) }
        )
    }
}