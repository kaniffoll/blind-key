package org.blindkey.domain.settings

import org.blindkey.domain.model.ThemeParam
import kotlinx.coroutines.flow.Flow

interface AppSettings {
    val theme: Flow<ThemeParam>
    suspend fun setTheme(theme: ThemeParam)
}