package org.blindkey.data.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object SettingsKeys {
    val THEME_PARAM = stringPreferencesKey("theme_param")
    val HAS_PUNCTUATION_PARAM = stringPreferencesKey("has_punctuation_param")
    val LANGUAGE_PARAM = stringPreferencesKey("language_param")
    val LENGTH_PARAM = stringPreferencesKey("length_param")
}