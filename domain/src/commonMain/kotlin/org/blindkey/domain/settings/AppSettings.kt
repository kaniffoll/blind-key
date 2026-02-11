package org.blindkey.domain.settings

import org.blindkey.domain.model.ThemeParam
import kotlinx.coroutines.flow.Flow
import org.blindkey.domain.model.TestParam

interface AppSettings {
    val theme: Flow<ThemeParam>
    suspend fun setTheme(theme: ThemeParam)

    suspend fun updateLocalData()

    suspend fun saveTestParameters(testParam: TestParam)

    suspend fun getTestParameters(): TestParam
}